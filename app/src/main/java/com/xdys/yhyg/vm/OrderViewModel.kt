package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xdys.library.base.BaseViewModel
import com.xdys.library.config.Constant
import com.xdys.library.event.DisposableLiveData
import com.xdys.library.network.HttpClient
import com.xdys.yhyg.api.OrderApi
import com.xdys.yhyg.entity.ListStatusParams
import com.xdys.yhyg.entity.order.OrderEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create3(OrderApi::class.java) }

    val listStatusLiveData by lazy { DisposableLiveData<ListStatusParams>() }

    val orderListLiveData by lazy { MutableLiveData<MutableList<OrderEntity>>() }

    private var page: Int = 1

    fun orderList(type: String, restart: Boolean) {
        if (restart) page = 1
        viewModelScope.launch {

            if (restart) page = 1
            fetchData({
                api.orderList(type, page, Constant.Config.SIZE)
            }, failure = {
                handleThrowable(it)
                listStatusLiveData.postValue(
                    (listStatusLiveData.value ?: ListStatusParams()).failure(page)
                )
            })?.let {
                orderListLiveData.postValue(
                    if (restart) it else orderListLiveData.value?.apply { addAll(it) }
                )
                listStatusLiveData.postValue(
                    (listStatusLiveData.value ?: ListStatusParams()).success(page, it.size)
                )
                page += 1
            }

        }
    }

}