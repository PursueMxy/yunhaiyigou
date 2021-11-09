package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.PageData
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.cart.CartGoods
import com.xdys.yhyg.entity.classify.CateEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClassificationApi : BaseApi {

    @GET("/mall-goods/api/goodsSpu/page")
    suspend fun goodsSpu(
        @Query("current") current: String,
        @Query("categoryShopFirst") categoryShopFirst: String
    ): Result<PageData<CartGoods>>

    @GET("/mall-goods/api/goodscatshop/tree")
    suspend fun goodsClassify(): Result<MutableList<CateEntity>>

    @GET("/mall-goods/api/goodscatshop/first/{shopId}")
    suspend fun goodsCat(@Path("shopId") shopId: String): Result<Any>

}