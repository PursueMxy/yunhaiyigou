package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.home.FavGoodsEntity

class FlashGoodsAdapter :
    BaseQuickAdapter<FavGoodsEntity, BaseViewHolder>(R.layout.item_flash_goods) {

    init {
        setDiffCallback(FlashGoodsDiffCallback())
    }

    override fun convert(
        holder: BaseViewHolder,
        item: FavGoodsEntity
    ) {
        holder.setText(R.id.tvGoodsName, item.name)
            .setText(R.id.tvPrice, item.priceDown)
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(item.picUrls[0], 6)
    }
}

class FlashGoodsDiffCallback : DiffUtil.ItemCallback<FavGoodsEntity>() {
    override fun areItemsTheSame(
        oldItem: FavGoodsEntity,
        newItem: FavGoodsEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: FavGoodsEntity,
        newItem: FavGoodsEntity
    ): Boolean {
        return oldItem.name == newItem.name && oldItem.picUrls == newItem.picUrls
                && oldItem.priceDown == newItem.priceDown
    }
}
