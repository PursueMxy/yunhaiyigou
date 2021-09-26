package com.xdys.yhyg.adapte.cart

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.cart.CartGoods

class CartGoodsAdapter : BaseQuickAdapter<CartGoods, BaseViewHolder>(R.layout.item_cart_goods) {

    init {
        setDiffCallback(CartItemDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: CartGoods) {

    }
}

class CartItemDiffCallback : DiffUtil.ItemCallback<CartGoods>() {
    override fun areItemsTheSame(oldItem: CartGoods, newItem: CartGoods): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CartGoods, newItem: CartGoods): Boolean {
        return oldItem.goodsName == newItem.goodsName
    }
}