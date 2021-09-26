package com.xdys.yhyg.entity.mine

data class CouponsEntity(
    val id: String? = null,
    val CouponsPrice: String? = null,
    val CouponConditions: String? = null,
    val CouponsType: String? = null,
    val title: String? = null,
    val time: String? = null,
    val type: String? = null,
)

data class DiscountCoupon(
    val id: String? = null,
    val title: String? = null,
    val content: String? = null,
)