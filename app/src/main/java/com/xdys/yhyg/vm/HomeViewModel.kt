package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.xdys.library.base.BaseViewModel
import com.xdys.library.config.Constant
import com.xdys.library.extension.context
import com.xdys.library.network.HttpClient
import com.xdys.library.network.base.PageData
import com.xdys.yhyg.R
import com.xdys.yhyg.api.HomeApi
import com.xdys.yhyg.entity.goods.GenerateOrdersEntity
import com.xdys.yhyg.entity.goods.GoodsDetailEntity
import com.xdys.yhyg.entity.home.BrandMerchantEntity
import com.xdys.yhyg.entity.home.FavGoodsEntity
import com.xdys.yhyg.entity.home.HomeBean
import com.xdys.yhyg.entity.home.SecCatEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class HomeViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create(HomeApi::class.java) }

    private val api2 by lazy { HttpClient.create3(HomeApi::class.java) }

    private val gson by lazy { Gson() }

    val conversionLiveData by lazy { MutableLiveData<Boolean>() }

    val homeLiveData by lazy { MutableLiveData<HomeBean>() }

    val favGoodsLiveData by lazy { MutableLiveData<PageData<FavGoodsEntity>>() }

    val shopFavLiveData by lazy { MutableLiveData<PageData<BrandMerchantEntity>>() }

    val secCarLiveData by lazy { MutableLiveData<MutableList<SecCatEntity>>() }

    val goodsDetailLiveData by lazy { MutableLiveData<GoodsDetailEntity>() }

    fun conversion(choose: Boolean) {
        conversionLiveData.postValue(choose)
    }


    /**
     * 首页数据
     */
    fun goodsPage() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.homeCat() })?.let {
                homeLiveData.postValue(it)
            }
        }
    }

    /**
     * 猜你喜欢
     */
    fun favGoods() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.favGoods() })?.let {
                favGoodsLiveData.postValue(it)
            }
        }
    }

    /**
     * 品牌商家
     */
    fun shopFav() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.shopFav() })?.let {
                shopFavLiveData.postValue(it)
            }
        }
    }

    /**
     * 分类列表
     */
    fun homeSecCat(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.homeSecCat(id) })?.let {
                secCarLiveData.postValue(it)
            }
        }
    }

    /**
     * 商品详情
     */
    fun goodsDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.goodsDetail(id) })?.let {
                goodsDetailLiveData.postValue(it)
            }
        }
    }

    /**
     * 下单
     */
    fun savaGoods(order: GenerateOrdersEntity) {
        val body = gson.toJson(order).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch {
            fetchData({ api2.generateOrders(body) })?.let {

            }
        }
    }

    /**
     * 商品保障
     */
    fun ensureBySpuId(id:String){
        viewModelScope.launch {
            fetchData({ api.ensureBySpuId(id) })?.let {

            }
        }
    }


}