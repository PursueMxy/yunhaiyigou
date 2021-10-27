package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.order.OrderEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface OrderApi : BaseApi {

    @GET("api/datasearch")
    suspend fun orderList(
        @Query("status") status: String,
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): Result<MutableList<OrderEntity>>

}