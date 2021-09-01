package com.xdys.library.event

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 一次性的LiveData，只有setValue或者postValue才会发送
 */
class DisposableLiveData<T> : MutableLiveData<T>() {

    private val used: AtomicBoolean = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, {
            if (used.compareAndSet(true, false)) observer.onChanged(it)
        })
    }

    override fun setValue(value: T) {
        used.set(true)
        super.setValue(value)
    }

    override fun postValue(value: T) {
        used.set(true)
        super.postValue(value)
    }
}