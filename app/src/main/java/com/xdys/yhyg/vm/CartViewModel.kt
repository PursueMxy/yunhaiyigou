package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xdys.library.base.BaseViewModel
import com.xdys.library.network.HttpClient
import com.xdys.yhyg.api.CartApi
import com.xdys.yhyg.entity.cart.CartEntity
import kotlinx.coroutines.launch

class CartViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create(CartApi::class.java) }

    val cartLiveData by lazy { MutableLiveData<CartEntity>() }


    fun cart() {
        viewModelScope.launch {
            fetchData({ api.cart("5abeb6e4-ff1c-4fce-a73d-fb600e463ab0") })?.let {
                cartLiveData.postValue(it)
            }
        }
    }
}
