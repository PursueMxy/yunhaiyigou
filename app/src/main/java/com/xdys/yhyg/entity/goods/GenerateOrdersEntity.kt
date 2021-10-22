package com.xdys.yhyg.entity.goods

data class GenerateOrdersEntity(
    val paymentWay: String? = null,
    val deliveryWay: String? = null,
    val paymentType: String? = null,
    val tradeType: String? = null,
    val freightPrice: String? = null,
    val salesPrice: String? = null,
    val paymentPointsPrice: String? = null,
    val paymentCouponPrice: String? = null,
    val paymentPoints: String? = null,
    val userMessage: String? = null,
    val ItemIds: MutableList<shopId> = mutableListOf()
)

data class shopId(
    val goodsId: String? = null,
    val skuId: String? = null,
    val goodsNum: String? = null
)