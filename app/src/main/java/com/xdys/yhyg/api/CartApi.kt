package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.cart.CartEntity
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CartApi : BaseApi {

    @GET("cart/index")
    suspend fun cart(
        @Query("token") token: String
    ): Result<CartEntity>

    @POST("/mall-user/api/user/cart")
    suspend fun addCart(@Body body: RequestBody): Result<Any>

    @GET("/mall-user/api/user/cart")
    suspend fun cartList():Result<CartEntity>
}