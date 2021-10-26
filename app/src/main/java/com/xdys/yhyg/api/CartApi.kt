package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.cart.CartEntity
import okhttp3.RequestBody
import retrofit2.http.*

interface CartApi : BaseApi {

    @GET("cart/index")
    suspend fun cart(
        @Query("token") token: String
    ): Result<CartEntity>

    @POST("/mall-user/api/user/cart")
    suspend fun addCart(@Body body: RequestBody): Result<Any>

    @GET("/mall-user/api/user/cart")
    suspend fun cartList(): Result<CartEntity>

    @DELETE("/mall-user/api/user/cart/{ids}")
    suspend fun deleteCart(@Path("ids") ids: String): Result<Any>

    @PUT("/mall-user/api/user/cart")
    suspend fun updateCart(@Body body: RequestBody): Result<Any>
}