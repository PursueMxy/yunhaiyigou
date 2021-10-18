package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xdys.library.base.BaseViewModel
import com.xdys.library.network.HttpClient
import com.xdys.library.network.base.PageData
import com.xdys.yhyg.api.HomeApi
import com.xdys.yhyg.entity.home.BannerBean
import com.xdys.yhyg.entity.home.HomeBean
import com.xdys.yhyg.entity.home.HomeClassifyBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create(HomeApi::class.java) }

    val conversionLiveData by lazy { MutableLiveData<Boolean>() }


    val classifyLiveData by lazy { MutableLiveData<HomeBean>() }

    fun conversion(choose: Boolean) {
        conversionLiveData.postValue(choose)
    }


    /**
     * 首页数据
     */
    fun goodsPage() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.goodsPage() })?.let {
                classifyLiveData.postValue(it)
            }
        }
    }


}