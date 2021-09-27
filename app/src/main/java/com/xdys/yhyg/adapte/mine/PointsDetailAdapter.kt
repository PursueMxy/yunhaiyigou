package com.xdys.yhyg.adapte.mine

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.PointsEntity

class PointsDetailAdapter :
    BaseQuickAdapter<PointsEntity, BaseViewHolder>(R.layout.item_points_detail) {

    init {
        setDiffCallback(PointsDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: PointsEntity) {
        holder.setText(R.id.tvTitle, "购物积分抵扣")
            .setText(R.id.tvTime, "2021-08-05  10:34")
            .setText(R.id.tvNumber, "+300")
    }
}

class PointsDiffCallback : DiffUtil.ItemCallback<PointsEntity>() {
    override fun areItemsTheSame(oldItem: PointsEntity, newItem: PointsEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PointsEntity, newItem: PointsEntity): Boolean {
        return oldItem.title == oldItem.title && oldItem.number == oldItem.number
                && oldItem.time == newItem.time
    }
}
