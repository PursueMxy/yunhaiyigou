package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.xdys.library.base.BaseViewModel
import com.xdys.library.config.Constant
import com.xdys.library.extension.context
import com.xdys.library.network.HttpClient
import com.xdys.yhyg.R
import com.xdys.yhyg.api.CartApi
import com.xdys.yhyg.entity.cart.CartEntity
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class CartViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create2(CartApi::class.java) }

    val cartLiveData by lazy { MutableLiveData<CartEntity>() }



    private val gson by lazy { Gson() }


    fun cart() {
        viewModelScope.launch {
            fetchData({ api.cart("5abeb6e4-ff1c-4fce-a73d-fb600e463ab0") })?.let {
                cartLiveData.postValue(it)
            }
        }
    }

    fun addCart(
        spuId: String, skuId: String, quantity: String, spuName: String, addPrice: String,
        specInfo: String, picUrl: String,
    ) {
        val map = hashMapOf(
            "spuId" to spuId, "skuId" to skuId, "quantity" to quantity,
            "spuName" to spuName, "addPrice" to addPrice, "specInfo" to specInfo, "picUrl" to picUrl
        )
        val body = gson.toJson(map).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch {
            fetchData({ api.addCart(body) })?.let {
            }
        }
    }

    fun cartList(){
        viewModelScope.launch {
            fetchData({ api.cartList() })?.let {
                cartLiveData.postValue(it)
            }
        }
    }
}
