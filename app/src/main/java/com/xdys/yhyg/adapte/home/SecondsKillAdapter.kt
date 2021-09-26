package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.goods.SecondsKill

class SecondsKillAdapter :
    BaseQuickAdapter<SecondsKill, BaseViewHolder>(R.layout.item_seconds_kill) {

    init {
        setDiffCallback(SecondsKillDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: SecondsKill) {
        holder.setText(R.id.tvGoodsName, item.goodsName)
            .setText(R.id.tvPrice, item.price)
            .setText(R.id.tvOriginalPrice, item.originalPrice)
            .setText(R.id.tvSold, item.sold)
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu)
    }
}


class SecondsKillDiffCallback : DiffUtil.ItemCallback<SecondsKill>() {
    override fun areItemsTheSame(
        oldItem: SecondsKill,
        newItem: SecondsKill
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: SecondsKill,
        newItem: SecondsKill
    ): Boolean {
        return oldItem.goodsName == newItem.goodsName && oldItem.price == newItem.price
                && oldItem.image == newItem.image && oldItem.sold == newItem.sold
                && oldItem.originalPrice == newItem.originalPrice
    }
}