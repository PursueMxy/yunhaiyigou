package com.xdys.yhyg.entity.order


data class OrderEntity(
    val orders_id: String? = null,
    val orders_count: String? = null,
    val order_goods_items: MutableList<GoodsEntity> = mutableListOf(),
    val status: String = "0"
)

data class GoodsEntity(
    val orderId: String? = null,
    val goodsNum: String? = null,
    val validTime: String? = null,
    val goodsId: String? = null,
    val signatureKey: String? = null,
    val goodsName: String? = null,
    val orderOfTime: String? = null,
    val orderOn: String? = null,
    val merchantId: String? = null,
    val goodsImages: String? = null,
    val goodsPrice: String? = null,
    val goodsState: String? = null,
    val goodsAmount_of: String? = null,
    val goodsNumber: String? = null,
    val goodsSpecifications: String? = null,
    val vendorName: String? = null,
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