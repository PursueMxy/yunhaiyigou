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
    val itemIds: ShopList? = null,
    val addressId: String? = null,
)

data class ShopList(
    val shopList: MutableList<GoodsList> = mutableListOf(),
)

data class GoodsList(
    val shopId: String? = null,
    val goodsList: MutableList<GoodsOrder> = mutableListOf(),
)


data class GoodsOrder(
    val goodsId: String? = null,
    val skuId: String? = null,
    val quantity: String? = null
)