package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.goods.Spike

class SpikeAdapter :
    BaseQuickAdapter<Spike, BaseViewHolder>(R.layout.item_spike) {

    init {
        setDiffCallback(SpikeDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: Spike) {
        holder.setText(R.id.tvName, item.goodsName)
            .setText(R.id.tvPrice, item.price)
            .setText(R.id.tvOriginalPrice, item.originalPrice)
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu)
    }
}


class SpikeDiffCallback : DiffUtil.ItemCallback<Spike>() {
    override fun areItemsTheSame(
        oldItem: Spike,
        newItem: Spike
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Spike,
        newItem: Spike
    ): Boolean {
        return oldItem.goodsName == newItem.goodsName && oldItem.price == newItem.price
                && oldItem.image == newItem.image && oldItem.originalPrice == newItem.originalPrice
    }
}