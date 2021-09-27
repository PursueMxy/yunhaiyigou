package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.entity.mine.ShopCollectionEntity

class ShopCollectionAdapter :
    BaseQuickAdapter<ShopCollectionEntity, BaseViewHolder>(R.layout.item_shop_collection) {

    init {
        setDiffCallback(ShopCollectionDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: ShopCollectionEntity) {
        holder.setText(R.id.tvShopName, "戈蔓婷女装专卖店")
            .setText(R.id.tvAttention, "1.2万人关注")
            .getView<ImageView>(R.id.ivShops).loadRoundCornerImage(R.mipmap.mall_logo)
    }
}

class ShopCollectionDiffCallback : DiffUtil.ItemCallback<ShopCollectionEntity>() {
    override fun areItemsTheSame(
        oldItem: ShopCollectionEntity,
        newItem: ShopCollectionEntity
    ): Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(
        oldItem: ShopCollectionEntity,
        newItem: ShopCollectionEntity
    ): Boolean {
        return oldItem.shopName == newItem.shopName && oldItem.attention == newItem.attention
                && oldItem.shops == oldItem.shops
    }
}