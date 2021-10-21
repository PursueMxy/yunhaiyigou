package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xdys.library.base.BaseViewModel
import com.xdys.library.network.HttpClient
import com.xdys.yhyg.api.ClassificationApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClassificationViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create(ClassificationApi::class.java) }

    val goodsSpuLiveData by lazy { MutableLiveData<Any>() }


    fun goodsSpu() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.goodsSpu() })?.let {
            }
        }
    }
}