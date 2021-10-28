package com.xdys.yhyg.adapte.home

import android.graphics.Paint
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.currency
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.home.SeckillGoods

class FlashSaleAdapter :
    BaseQuickAdapter<SeckillGoods, BaseViewHolder>(R.layout.item_seconds_kill) {

    init {
        setDiffCallback(FlashSaleDiffCallback())
        addChildClickViewIds(R.id.tvGoPanicBuying)
    }

    override fun convert(holder: BaseViewHolder, item: SeckillGoods) {
        val sold = item.seckillNum % item.limitNum
        holder.setText(R.id.tvGoodsName, item.name)
            .setText(R.id.tvPrice, item.seckillPrice?.currency())
            .setText(R.id.tvOriginalPrice, "￥${item.salesPrice}")
            .setText(R.id.tvSold, "已售:$sold%")
            .setText(R.id.tvSpecialOffers, item.shareTitle)
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(item.picUrl)
        var pbSellProgress = holder.getView<ProgressBar>(R.id.pbSellProgress)
        pbSellProgress.max = item.limitNum
        pbSellProgress.progress = item.seckillNum
        holder.getView<TextView>(R.id.tvOriginalPrice).paintFlags =
            Paint.STRIKE_THRU_TEXT_FLAG
    }
}


class FlashSaleDiffCallback : DiffUtil.ItemCallback<SeckillGoods>() {
    override fun areItemsTheSame(
        oldItem: SeckillGoods,
        newItem: SeckillGoods
    ): Boolean {
        return oldItem.skuId == newItem.skuId
    }

    override fun areContentsTheSame(
        oldItem: SeckillGoods,
        newItem: SeckillGoods
    ): Boolean {
        return oldItem.spuId == newItem.spuId && oldItem.name == newItem.name
                && oldItem.seckillPrice == newItem.seckillPrice && oldItem.seckillNum == newItem.seckillNum
                && oldItem.salesPrice == newItem.salesPrice && oldItem.limitNum == newItem.limitNum
    }
}
