package com.xdys.yhyg.api

import com.xdys.library.entity.PayParametersEntity
import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.PageData
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.goods.SaveOrderEntity
import com.xdys.yhyg.entity.order.OrderAddress
import com.xdys.yhyg.entity.order.OrderDetail
import com.xdys.yhyg.entity.order.OrderEntity
import com.xdys.yhyg.entity.order.PreviewOrderEntity
import okhttp3.RequestBody
import retrofit2.http.*

interface OrderApi : BaseApi {

    @GET("/mall-order/api/orderinfo/page")
    suspend fun orderList(
        @QueryMap map: Map<String, String>
    ): Result<PageData<OrderEntity>>

    @POST("api/order_details")
    suspend fun orderDetail(@Body body: RequestBody): Result<OrderDetail>

    @GET("api/check_logistics")
    suspend fun logistics(@Query("courier_number") courier_number: String): Result<OrderAddress>


    @POST("/mall-order/api/orderinfo/foldOrder")
    suspend fun foldOrder(@Body body: RequestBody): Result<PreviewOrderEntity>

    @POST("/mall-order/api/orderinfo/addOrder")
    suspend fun addOrder(@Body body: RequestBody): Result<MutableList<SaveOrderEntity>>

    @POST("/mall-order/api/pay/payment")
    suspend fun orderPay(@Body body: RequestBody): Result<PayParametersEntity>

    @PUT("/mall-order/api/orderinfo/receive/{id}")
    suspend fun orderReceive(@Path("id") id: String): Result<Any>

    @GET("/mall-order/api/orderinfo/{id}")
    suspend fun orderInfo(@Path("id") id: String): Result<Any>
}