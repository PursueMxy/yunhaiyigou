package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xdys.library.base.BaseViewModel
import com.xdys.library.network.HttpClient
import com.xdys.library.network.base.PageData
import com.xdys.yhyg.api.ClassificationApi
import com.xdys.yhyg.entity.cart.CartGoods
import com.xdys.yhyg.entity.classify.CateEntity
import com.xdys.yhyg.entity.goods.GoodsDetailEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClassificationViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create(ClassificationApi::class.java) }


    val goodsClassifyLiveData by lazy { MutableLiveData<MutableList<CateEntity>>() }

    val goodsSpuLiveData by lazy { MutableLiveData<PageData<CartGoods>>() }

    fun goodsSpu(categoryShopFirst: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.goodsSpu("1", categoryShopFirst) })?.let {
                goodsSpuLiveData.postValue(it)
            }
        }
    }

    fun goodsClassify() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.goodsClassify() })?.let {
                goodsClassifyLiveData.postValue(it)
            }
        }
    }

    fun goodsCat() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.goodsCat("") })?.let {
            }
        }
    }
}