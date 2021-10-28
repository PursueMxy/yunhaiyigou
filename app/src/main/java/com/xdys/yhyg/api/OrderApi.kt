package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.order.OrderAddress
import com.xdys.yhyg.entity.order.OrderDetail
import com.xdys.yhyg.entity.order.OrderEntity
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderApi : BaseApi {

    @GET("api/datasearch")
    suspend fun orderList(
        @Query("status") status: String,
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): Result<MutableList<OrderEntity>>

    @POST("api/order_details")
    suspend fun orderDetail(@Body body: RequestBody): Result<OrderDetail>

    @GET("api/check_logistics")
    suspend fun logistics(@Query("courier_number") courier_number: String): Result<OrderAddress>

}