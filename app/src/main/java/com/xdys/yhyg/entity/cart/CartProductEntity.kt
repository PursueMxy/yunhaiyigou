package com.xdys.yhyg.entity.cart

import com.chad.library.adapter.base.entity.node.BaseNode
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class CartEntity(
    var list: MutableList<CartShopEntity> = mutableListOf()
) : Serializable {
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
    val userShoppingCartId: String = "",
    val goodsSpu: GoodsSpu? = GoodsSpu(),
    val skuId: String = "",
    val spuId: String = "",
    var quantity: Long = 0,
    val goodsSku: GoodsSku,
    val specs: MutableList<Specs> = mutableListOf()
) : Serializable, BaseNode() {
    override val childNode: MutableList<BaseNode>? = null
    var selected = false
}

data class Specs(
    val specValueId: String? = "",
    val specValueName: String? = ""
) : Serializable


data class GoodsSku(
    val id: String? = null,
    val shopId: String? = null,
    val stock: Long = 0,
    val salesPrice: String? = "0.00"
) : Serializable

data class GoodsSpu(
    val id: String? = null,
    val spuCode: String? = null,
    val name: String? = null,
    val picUrls: String? = null,
    val priceDown: String? = null,
    val priceUp: String? = null
) : Serializable


data class CartGoods(
    val tenantId: String? = null,
    val id: String? = null,
    val shopId: String? = null,
    val spuCode: String? = null,
    val name: String? = null,
    val sellPoint: String? = null,
    val description: String? = null,
    val categoryFirst: String? = null,
    val categorySecond: String? = null,
    val categoryShopFirst: String? = null,
    val categoryShopSecond: String? = null,
    val picUrls: MutableList<String> = mutableListOf(),
    val shelf: String? = null,
    val sort: String? = null,
    val priceDown: String? = null,
    val priceUp: String? = null,
    val saleNum: String? = null,
    val specType: String? = null,
    val pointsGiveSwitch: String? = null,
    val pointsGiveNum: String? = null,
    val pointsDeductSwitch: String? = null,
    val pointsDeductScale: String? = null,
    val pointsDeductAmount: String? = null,
    val freightTemplatId: String? = null,
    val verifyStatus: String? = null,
    val verifyDetail: String? = null,
    val isHot: String? = null,
) : Serializable