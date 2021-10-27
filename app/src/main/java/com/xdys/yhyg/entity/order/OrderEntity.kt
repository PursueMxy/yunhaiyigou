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

data class OrderProductEntity(
    val id: String? = null,
    val goodsName: String? = null,
    val specification: String? = null,
    val price: String? = null,
    val goodsNum: String? = null,
    val image: String? = null,
)