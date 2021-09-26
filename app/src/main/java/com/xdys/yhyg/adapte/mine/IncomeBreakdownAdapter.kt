package com.xdys.yhyg.adapte.mine

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.IncomeBreakdownEntity

class IncomeBreakdownAdapter :
    BaseQuickAdapter<IncomeBreakdownEntity, BaseViewHolder>(R.layout.item_points_detail) {

    init {
        setDiffCallback(IncomeBreakdownDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: IncomeBreakdownEntity) {
        holder.setText(R.id.tvTitle, "购物积分抵扣")
            .setText(R.id.tvTime, "2021-08-05  10:34")
            .setText(R.id.tvNumber, "+300")
    }
}

class IncomeBreakdownDiffCallback : DiffUtil.ItemCallback<IncomeBreakdownEntity>() {
    override fun areItemsTheSame(
        oldItem: IncomeBreakdownEntity,
        newItem: IncomeBreakdownEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: IncomeBreakdownEntity,
        newItem: IncomeBreakdownEntity
    ): Boolean {
        return oldItem.time == newItem.time && oldItem.title == newItem.title
                && oldItem.DiscountAmount == newItem.DiscountAmount
    }
}
