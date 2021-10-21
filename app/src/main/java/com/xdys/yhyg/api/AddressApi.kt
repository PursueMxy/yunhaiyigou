package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.address.AddressEntity
import com.xdys.yhyg.entity.cart.CartEntity
import okhttp3.RequestBody
import retrofit2.http.*

interface AddressApi : BaseApi {

    @POST("/mall-user/api/address")
    suspend fun saveAddress(@Body body: RequestBody): Result<Any>

    @DELETE("/mall-user/api/address/{ids}")
    suspend fun addressDelete(@Path("ids") id: String): Result<Any>

    @GET("/mall-user/api/address")
    suspend fun addressList(): Result<MutableList<AddressEntity>>


    @PUT("/mall-user/api/address")
    suspend fun updateAddress(@Body body: RequestBody): Result<Any>

    @GET("/mall-user/api/address/default")
    suspend fun defaultAddress(): Result<AddressEntity>

}