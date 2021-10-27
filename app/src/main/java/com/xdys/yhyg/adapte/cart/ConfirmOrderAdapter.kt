package com.xdys.yhyg.adapte.cart

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.currency
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.cart.CartProductEntity
import com.xdys.yhyg.entity.cart.CartShopEntity

class ConfirmOrderAdapter : BaseNodeAdapter() {

    init {
        addFullSpanNodeProvider(ShopProvider())
        addNodeProvider(OrderGoodsProvider())
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int =
        when (data[position]) {
            is CartShopEntity -> 0
            is CartProductEntity -> 1
            else -> 1
        }
}

class ShopProvider : BaseNodeProvider() {

    override val itemViewType: Int = 0
    override val layoutId: Int = R.layout.item_order_shop

    override fun convert(holder: BaseViewHolder, item: BaseNode) {
        (item as CartShopEntity)?.let {
            holder.setText(R.id.tvShopName, it.shopName)
        }
    }
}


class OrderGoodsProvider : BaseNodeProvider() {

    override val itemViewType: Int = 1
    override val layoutId: Int = R.layout.item_order_goods

    override fun convert(holder: BaseViewHolder, item: BaseNode) {
        (item as CartProductEntity)?.let {
            holder.setText(R.id.tvGoodsName, it.goodsSpu?.name)
                .setText(R.id.tvSpecs, it.specs.get(0).specValueName)
                .setText(R.id.tvPrice, it.goodsSku.salesPrice?.currency())
                .setText(R.id.tvNumber, it.quantity.toString())
        }
    }
}
