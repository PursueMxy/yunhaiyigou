package com.xdys.yhyg.entity.cart

import com.chad.library.adapter.base.entity.node.BaseNode

data class CartShopEntity(
    val shopName: String? = "",
    val goodsList: MutableList<CartProductEntity> = mutableListOf()
) : BaseNode() {
    override var childNode: MutableList<BaseNode>? = null
}

data class CartProductEntity(
    val cartId: Long = 0,
    val goodsName: String? = "",
) : BaseNode() {
    override val childNode: MutableList<BaseNode>? = null
    var selected = false
}