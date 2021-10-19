package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.PageData
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.home.BrandMerchantEntity
import com.xdys.yhyg.entity.home.FavGoodsEntity
import com.xdys.yhyg.entity.home.HomeBean
import com.xdys.yhyg.entity.home.SecCatEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApi : BaseApi {


    @GET("/mall-goods/api/homecat")
    suspend fun homeCat(): Result<HomeBean>

    @GET("/mall-goods/api/homecat/favGoods")
    suspend fun favGoods(): Result<PageData<FavGoodsEntity>>

    @GET("/mall-shop/api/shop/fav")
    suspend fun shopFav(): Result<PageData<BrandMerchantEntity>>

    @GET("/mall-goods/api/homecat/secCat/{id}")
    suspend fun homeSecCat(@Path("id") id: String): Result<MutableList<SecCatEntity>>

}