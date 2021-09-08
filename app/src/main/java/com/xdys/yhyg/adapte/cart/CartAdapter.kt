package com.xdys.yhyg.adapte.cart

import android.util.Log
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.cart.CartProductEntity
import com.xdys.yhyg.entity.cart.CartShopEntity

class CartAdapter() : BaseNodeAdapter() {

    init {
        addFullSpanNodeProvider(CartShopProvider())
        addNodeProvider(CartProductProvider())
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int =
        when (data[position]) {
            is CartShopEntity -> 0
            is CartProductEntity -> 1
            else -> 1
        }
}


class CartShopProvider : BaseNodeProvider() {

    override val itemViewType: Int = 0
    override val layoutId: Int = R.layout.item_cart

    override fun convert(holder: BaseViewHolder, item: BaseNode) {
    }
}

class CartProductProvider : BaseNodeProvider() {

    override val itemViewType: Int = 1
    override val layoutId: Int = R.layout.item_cart_goods

    override fun convert(holder: BaseViewHolder, item: BaseNode) {
    }
}

