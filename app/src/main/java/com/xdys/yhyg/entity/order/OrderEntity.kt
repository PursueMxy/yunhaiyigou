package com.xdys.yhyg.entity.order


data class OrderEntity(
    val orders_id: String? = null,
    val orders_count: String? = null,
    val listOrderItem: MutableList<GoodsEntity> = mutableListOf(),
    val status: String = "0"
)

data class GoodsEntity(
    val orderId: String? = null,
    val spuId: String? = null,
    val spuName: String? = null,
    val id: String? = null,
    val skuId: String? = null,
    val specInfo: String? = null,
    val quantity: String? = null,
    val salesPrice: String? = null,
    val tenantId: String? = null,
    val freightPrice: String? = null,
    val paymentPrice: String? = null,
    val paymentPointsPrice: String? = null,
    val paymentCouponPrice: String? = null,
    val paymentPoints: String? = null,
    val status: String? = null,
    val isRefund: String? = null,
)


data class OrderDetail(
    val coupon_discount: String? = null,
    val valid_time: String? = null,
    val courier_number: String? = null,
    val online_discount: String? = null,
    val freight: String? = null,
    val merchant_id: String? = null,
    val place_time: String? = null,
    val payment_time: String? = null,
    val user_id: String? = null,
    val goods_amount: String? = null,
    val order_on: String? = null,
    val order_status: String? = null,
    val distribution: String? = null,
    val method_payment: String? = null,
    val goods: GoodsDetailEntity? = null


)

data class GoodsDetailEntity(
    val num: String? = null,
    val data: MutableList<GoodsEntity> = mutableListOf()
)


data class OrderAddress(
    val address: String? = null,
    val Name: String? = null,
    val telNum: String? = null
)