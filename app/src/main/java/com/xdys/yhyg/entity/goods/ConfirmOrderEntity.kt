package com.xdys.yhyg.entity.goods

import java.io.Serializable

data class ConfirmOrderEntity(
    val shopName: String? = null,
    val shopId: String? = null,
    val goodsList: MutableList<OrderGoods> = mutableListOf()
) : Serializable

data class OrderGoods(
    val goodsId: String? = null,
    val goodsName: String? = null,
    val spuId: String? = null,
    val skuId: String? = null,
    val specValueName: String? = null,
    var quantity: Long = 0,
    val price: String? = "",
    val url: String? = null,
) : Serializable