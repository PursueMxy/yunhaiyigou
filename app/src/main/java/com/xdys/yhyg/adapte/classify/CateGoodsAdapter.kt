package com.xdys.yhyg.adapte.cart

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.currency
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.cart.CartGoods

class CateGoodsAdapter : BaseQuickAdapter<CartGoods, BaseViewHolder>(R.layout.item_cate_goods) {

    init {
        setDiffCallback(CartItemDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: CartGoods) {
        holder.setText(R.id.tvGoodsName, item.name)
            .setText(R.id.tvPrice, item.priceDown?.currency())
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(item.picUrls[0], 3)
    }
}

class CartItemDiffCallback : DiffUtil.ItemCallback<CartGoods>() {
    override fun areItemsTheSame(oldItem: CartGoods, newItem: CartGoods): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CartGoods, newItem: CartGoods): Boolean {
        return oldItem.name == newItem.name && oldItem.priceDown == newItem.priceDown
                && oldItem.priceUp == newItem.priceUp
    }
}