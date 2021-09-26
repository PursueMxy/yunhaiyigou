package com.xdys.yhyg.entity.cart

import com.chad.library.adapter.base.entity.node.BaseNode
import com.google.gson.annotations.SerializedName


data class CartEntity(
    val cartList: MutableList<CartShopEntity> = mutableListOf()
) {
    var totalPrice: String = "0"
}

data class CartShopEntity(
    @SerializedName("shop_id")
    val shopId: Int,
    @SerializedName("shop_name")
    val shopName: String = "",
    @SerializedName("shop_img")
    val shopImg: String = "",
    @SerializedName("goods_list")
    val goodsList: MutableList<CartProductEntity> = mutableListOf()
) : BaseNode() {
    override val childNode: MutableList<BaseNode>
        get() = goodsList as MutableList<BaseNode>
    var selected = false
}

data class CartProductEntity(
    @SerializedName("cart_id")
    val cartId: Long = 0,
    @SerializedName("goods_name")
    val goodsName: String = "",
) : BaseNode() {
    override val childNode: MutableList<BaseNode>? = null
    var selected = false
}


data class CartGoods(
    var id: String? = null,
    var goodsName: String? = null,
)