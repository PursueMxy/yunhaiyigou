package com.xdys.yhyg.entity.order

import com.chad.library.adapter.base.entity.node.BaseNode
import com.xdys.yhyg.entity.address.AddressEntity

data class PreviewOrderEntity(
    val buyerAddressId: String? = null,
    val shippingAddressApiVo: AddressEntity? = null,
    val paymentType: String? = null,
    val buyShopList: MutableList<BuyShop> = mutableListOf()
)


data class BuyShop(
    val shopId: String? = null,
    val deliveryMode: String? = null,
    val goodsList: MutableList<OrderGoods> = mutableListOf(),
    val orderType: String? = null,
    val freight: String? = null,
    val goodsTotalSalesPrice: String? = null,
    val paymentPrice: String? = null,
) : BaseNode() {
    override val childNode: MutableList<BaseNode>
        get() = goodsList as MutableList<BaseNode>
}

data class OrderGoods(
    val shopId: String? = null,
    val spuId: String? = null,
    val skuId: String? = null,
    val quantity: String? = null,
    val orderType: String? = null,
    val deliveryMode: String? = null,
    val paymentPrice: String? = null,
    val goodsSpuVo: GoodsSpuVo? = null,
    val goodsSkuVo: GoodsSkuVo? = null,
) : BaseNode() {
    override val childNode: MutableList<BaseNode>? = null
}

data class GoodsSpuVo(
    val id: String? = null,
    val name: String? = null,
    val picUrls: MutableList<String> = mutableListOf(),
    val shopId: String? = null,
)

data class GoodsSkuVo(
    val id: String? = null,
    val specName: String? = null,
    val stock: String? = null,
)