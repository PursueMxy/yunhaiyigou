package com.xdys.yhyg.entity.goods

import java.io.Serializable

data class GenerateOrdersEntity(
    val userShoppingCartIdList: MutableList<String> = mutableListOf(),
    val buyerAddressId: String? = null,
    val paymentType: String? = null,
    val buyShopList: MutableList<BuyShopEntity> = mutableListOf()
)

data class BuyShopEntity(
    val shopId: String? = null,
    val deliveryMode: String? = null,
    val buyerRemark: String? = null,
    val goodsList: MutableList<BuyGoodsEntity> = mutableListOf(),
    val userCouponId: String? = null,
    val orderType: String? = null,
    val marketId: String? = null,
    val relationId: String? = null,
)

data class BuyGoodsEntity(
    val shopId: String? = null,
    val spuId: String? = null,
    val skuId: String? = null,
    val quantity: String? = null,
    val seckillskuId: String? = null,
    val orderType: String? = null,
    val hasEnable: String? = null,
    val deliveryMode: String? = null,

    )


data class FoldOrder(
    var buyerAddressId: String? = null,
    var goodsList: MutableList<FoldGoods> = mutableListOf()
) : Serializable

data class FoldGoods(
    val shopId: String? = null,
    val spuId: String? = null,
    val skuId: String? = null,
    val quantity: String? = null,
    val seckillskuId: String? = null,
    val orderType: String? = null,
    val deliveryMode: String? = null,
    val cartId: String? = null,
) : Serializable