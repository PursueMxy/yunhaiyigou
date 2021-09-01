package com.xdys.library.event

import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.reflect.Field
import java.lang.reflect.Method

@Suppress("UNCHECKED_CAST")
object LiveDataBus {
    private val bus = arrayMapOf<String, MutableLiveData<*>>()

    fun post(data: Any) {
        val key = data::class.java.simpleName
        if (!bus.containsKey(key)) bus[key] = BusMutableLiveData<Any>()
        (bus[key] as MutableLiveData<Any>).postValue(data)
    }

    fun <T> toObserve(clazz: Class<T>): MutableLiveData<T> {
        val key = clazz.simpleName
        if (!bus.containsKey(key)) bus[key] = BusMutableLiveData<T>()
        return (bus[key] as MutableLiveData<T>)
    }

    private class BusMutableLiveData<T> : MutableLiveData<T>() {

        private val observerMap = ArrayMap<Observer<in T>, Observer<in T>>()

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)
            try {
                hook(observer)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun observeForever(observer: Observer<in T>) {
            if (!observerMap.containsKey(observer)) {
                observerMap[observer] = ObserverWrapper(observer)
            }
            super.observeForever(observer)
        }

        override fun removeObserver(observer: Observer<in T>) {
            val realObserver = if (observerMap.containsKey(observer)) {
                (observerMap.remove(observer) as Observer<in T>)
            } else observer
            super.removeObserver(realObserver)
        }

        private fun hook(observer: Observer<in T>) {
            val clazz = LiveData::class.java
            val fieldObservers = clazz.getDeclaredField("mObservers")
                .apply { isAccessible = true }
            val objectObservers = fieldObservers[this]
            val classObservers: Class<*> = objectObservers.javaClass
            val methodGet: Method = classObservers.getDeclaredMethod("get", Any::class.java)
                .apply { isAccessible = true }
            val objectWrapperEntry: Any? = methodGet.invoke(objectObservers, observer)
            val objectWrapper: Any =
                (if (objectWrapperEntry is Map.Entry<*, *>) objectWrapperEntry.value else null)
                    ?: throw NullPointerException("Wrapper can not be bull!")
            val fieldLastVersion: Field = objectWrapper.javaClass.superclass
                .getDeclaredField("mLastVersion").apply { isAccessible = true }
            //get livedata's version
            val fieldVersion: Field = clazz.getDeclaredField("mVersion")
                .apply { isAccessible = true }
            //set wrapper's version
            fieldLastVersion.set(objectWrapper, fieldVersion.get(this))
        }
    }

    private class ObserverWrapper<T>(val observer: Observer<T>) : Observer<T> {
        override fun onChanged(t: T) {
            if (isCallOnObserve()) return
            observer.onChanged(t)
        }

        private fun isCallOnObserve(): Boolean {
            val stackTrace = Thread.currentThread().stackTrace
            if (stackTrace.isNotEmpty()) {
                for (element in stackTrace) {
                    if ("androidx.lifecycle.LiveData" == element.className &&
                        "observeForever" == element.methodName
                    ) return true
                }
            }
            return false
        }
    }
}