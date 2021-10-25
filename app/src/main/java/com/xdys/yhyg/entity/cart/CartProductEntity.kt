package com.xdys.yhyg.entity.cart

import com.chad.library.adapter.base.entity.node.BaseNode
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class CartEntity(
    val list: MutableList<CartShopEntity> = mutableListOf()
) {
    var totalPrice: String = "0"
}

data class CartShopEntity(
    val shopId: String,
    val shopName: String = "",
    val goodsList: MutableList<CartProductEntity> = mutableListOf()
) : Serializable, BaseNode() {
    override val childNode: MutableList<BaseNode>
        get() = goodsList as MutableList<BaseNode>
    var selected = false
}

data class CartProductEntity(
    val cartId: Long = 0,
    val goodsSpu: GoodsSpu? = GoodsSpu(),
    val skuId: String = "",
    val goodsSku: GoodsSku,
) : Serializable, BaseNode() {
    override val childNode: MutableList<BaseNode>? = null
    var selected = false
}

data class GoodsSku(
    val id: String? = null,
    val shopId: String? = null
)

data class GoodsSpu(
    val id: String? = null,
    val spuCode: String? = null,
    val name: String? = null,
    val picUrls: String? = null,
    val priceDown: String? = null,
    val priceUp: String? = null
)


data class CartGoods(
    var id: String? = null,
    var goodsName: String? = null,
) : Serializable