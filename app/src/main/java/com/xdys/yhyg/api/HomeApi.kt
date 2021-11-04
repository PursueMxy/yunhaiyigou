package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.PageData
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.goods.EnsureByEntity
import com.xdys.yhyg.entity.goods.GoodsDetailEntity
import com.xdys.yhyg.entity.goods.SkuItem
import com.xdys.yhyg.entity.home.*
import okhttp3.RequestBody
import retrofit2.http.*

interface HomeApi : BaseApi {


    @GET("/mall-goods/api/homecat")
    suspend fun homeCat(): Result<HomeBean>

    @GET("/mall-goods/api/homecat/favGoods")
    suspend fun favGoods(): Result<PageData<FavGoodsEntity>>

    @GET("/mall-shop/api/shop/fav")
    suspend fun shopFav(): Result<PageData<BrandMerchantEntity>>

    @GET("/mall-goods/api/homecat/secCat/{id}")
    suspend fun homeSecCat(@Path("id") id: String): Result<MutableList<SecCatEntity>>

    @GET("/mall-goods/api/goodsSpu/{id}")
    suspend fun goodsDetail(@Path("id") id: String): Result<GoodsDetailEntity>

    @POST("api/datainsert")
    suspend fun generateOrders(@Body body: RequestBody): Result<Any>

    @GET("/mall-goods/api/goodsSpu/listEnsureBySpuId")
    suspend fun ensureBySpuId(@Query("spuId") spuId: String): Result<MutableList<EnsureByEntity>>

    @GET("/mall-goods/api/goodsSpu/spuspec/tree")
    suspend fun goodsSpu(@Query("spuId") spuId: String): Result<MutableList<SkuItem>>


    @GET("/mall-goods/api/seckillHall/page")
    suspend fun seckillHall(
        @Query("pageSize") pageSize: Int,
        @Query("pageNum") pageNum: Int
    ): Result<SeckillData>

    @GET("/mall-goods/api/coupon/page")
    suspend fun couponPage(@Body body: RequestBody): Result<Any>


    @GET("/mall-goods/api/goodsSpu/page")
    suspend fun goodsSpuPage(
        @Query("sortColumn") sortColumn: String,
        @Query("asc") asc: String
    ): Result<PageData<FavGoodsEntity>>
}