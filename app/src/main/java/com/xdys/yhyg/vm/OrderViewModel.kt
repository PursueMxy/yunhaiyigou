package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.xdys.library.base.BaseViewModel
import com.xdys.library.config.Constant
import com.xdys.library.event.DisposableLiveData
import com.xdys.library.extension.context
import com.xdys.library.network.HttpClient
import com.xdys.library.network.base.PageData
import com.xdys.yhyg.R
import com.xdys.yhyg.api.OrderApi
import com.xdys.yhyg.entity.ListStatusParams
import com.xdys.yhyg.entity.goods.FoldOrder
import com.xdys.yhyg.entity.goods.GenerateOrdersEntity
import com.xdys.yhyg.entity.order.OrderAddress
import com.xdys.yhyg.entity.order.OrderDetail
import com.xdys.yhyg.entity.order.OrderEntity
import com.xdys.yhyg.entity.order.PreviewOrderEntity
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.Collections.addAll

class OrderViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create2(OrderApi::class.java) }

    private val gson by lazy { Gson() }

    val listStatusLiveData by lazy { DisposableLiveData<ListStatusParams>() }

    val orderListLiveData by lazy { MutableLiveData<PageData<OrderEntity>>() }

    val orderDetailLiveData by lazy { MutableLiveData<OrderDetail>() }

    val addressLiveData by lazy { MutableLiveData<OrderAddress>() }

    val previewOrderLiveData by lazy { MutableLiveData<PreviewOrderEntity>() }

    val saveOrderLiveData by lazy { MutableLiveData<Any>() }

    private var page: Int = 1

    fun orderList(map: HashMap<String, String>, restart: Boolean) {
        if (restart) page = 1
        viewModelScope.launch {
            if (restart) page = 1
            fetchData({
                map["current"] = page.toString()
                map["size"] = Constant.Config.SIZE.toString()
                api.orderList(map)
            }, failure = {
                handleThrowable(it)
                listStatusLiveData.postValue(
                    (listStatusLiveData.value ?: ListStatusParams()).failure(page)
                )
            })?.let {
                orderListLiveData.postValue(
                    if (restart) it else orderListLiveData.value?.apply { addAll(it.records) }
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

    fun foldOrder(foldOrder: FoldOrder) {
        val body = gson.toJson(foldOrder).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch {
            fetchData({ api.foldOrder(body) })?.let {
                previewOrderLiveData.postValue(it)
            }
        }
    }

    fun addOrder(generateOrders: GenerateOrdersEntity) {
        val body = gson.toJson(generateOrders).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch {
            fetchData({ api.addOrder(body) })?.let {
                saveOrderLiveData.postValue(it)
            }
        }
    }

}