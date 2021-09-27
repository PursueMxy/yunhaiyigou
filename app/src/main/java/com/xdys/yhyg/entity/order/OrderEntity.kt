package com.xdys.yhyg.entity.order

data class OrderEntity(
    val id: String? = null,
    val shopName: String? = null,
    val orderStatus: String? = null,
    val orderTips: String? = null,
    val price: String? = null,
    val goodsNum: String? = null,
)

data class OrderProductEntity(
    val id: String? = null,
    val goodsName: String? = null,
    val specification: String? = null,
    val price: String? = null,
    val goodsNum: String? = null,
    val image: String? = null,
)