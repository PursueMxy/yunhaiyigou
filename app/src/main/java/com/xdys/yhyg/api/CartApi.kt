package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.cart.CartEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface CartApi : BaseApi {

    @GET("cart/index")
    suspend fun cart(
        @Query("token") token: String
    ): Result<CartEntity>
}