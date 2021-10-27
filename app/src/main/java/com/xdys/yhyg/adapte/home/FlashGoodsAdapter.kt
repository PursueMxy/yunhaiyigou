package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.currency
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.home.SeckillGoods

class FlashGoodsAdapter :
    BaseQuickAdapter<SeckillGoods, BaseViewHolder>(R.layout.item_flash_goods) {

    init {
        setDiffCallback(FlashGoodsDiffCallback())
    }

    override fun convert(
        holder: BaseViewHolder,
        item: SeckillGoods
    ) {
        holder.setText(R.id.tvGoodsName, item.name)
            .setText(R.id.tvPrice, item.seckillPrice?.currency())
            .setText(R.id.tvOriginalPrice, "￥${item.seckillPrice}")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(item.picUrl, 6)
    }
}

class FlashGoodsDiffCallback : DiffUtil.ItemCallback<SeckillGoods>() {
    override fun areItemsTheSame(
        oldItem: SeckillGoods,
        newItem: SeckillGoods
    ): Boolean {
        return oldItem.spuId == newItem.spuId
    }

    override fun areContentsTheSame(
        oldItem: SeckillGoods,
        newItem: SeckillGoods
    ): Boolean {
        return oldItem.name == newItem.name && oldItem.skuId == newItem.skuId
                && oldItem.name == newItem.name && oldItem.seckillPrice == newItem.seckillPrice
    }
}
