package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.xdys.library.base.BaseViewModel
import com.xdys.library.config.Constant
import com.xdys.library.event.DisposableLiveData
import com.xdys.library.extension.context
import com.xdys.library.network.HttpClient
import com.xdys.yhyg.R
import com.xdys.yhyg.api.OrderApi
import com.xdys.yhyg.entity.ListStatusParams
import com.xdys.yhyg.entity.order.OrderAddress
import com.xdys.yhyg.entity.order.OrderDetail
import com.xdys.yhyg.entity.order.OrderEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class OrderViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create3(OrderApi::class.java) }

    private val gson by lazy { Gson() }

    val listStatusLiveData by lazy { DisposableLiveData<ListStatusParams>() }

    val orderListLiveData by lazy { MutableLiveData<MutableList<OrderEntity>>() }

    val orderDetailLiveData by lazy { MutableLiveData<OrderDetail>() }

    val addressLiveData by lazy { MutableLiveData<OrderAddress>() }

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


    fun orderDetail(orderId: String) {
        val map = hashMapOf("order_id" to orderId)
        val body = gson.toJson(map).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch {
            fetchData({ api.orderDetail(body) })?.let {
                orderDetailLiveData.postValue(it)
            }
        }
    }

    fun logistics(orderId: String) {
        viewModelScope.launch {
            fetchData({ api.logistics(orderId) })?.let {
                addressLiveData.postValue(it)
            }
        }
    }

}