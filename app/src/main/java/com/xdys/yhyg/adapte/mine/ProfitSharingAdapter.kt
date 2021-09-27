package com.xdys.yhyg.adapte.mine

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.ProfitSharEntity

class ProfitSharingAdapter :
    BaseQuickAdapter<ProfitSharEntity, BaseViewHolder>(R.layout.item_profit_sharing) {

    init {
        setDiffCallback(ProfitSharDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: ProfitSharEntity) {
        holder.setText(R.id.tvTitle, "云网点推广收益")
            .setText(R.id.tvTime, "2021-08-05  10:34")
            .setText(R.id.tvIncome, "+300")
    }
}

class ProfitSharDiffCallback : DiffUtil.ItemCallback<ProfitSharEntity>() {
    override fun areItemsTheSame(oldItem: ProfitSharEntity, newItem: ProfitSharEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProfitSharEntity, newItem: ProfitSharEntity): Boolean {
        return oldItem.title == newItem.title && oldItem.time == oldItem.time
                && oldItem.inCome == newItem.inCome
    }
}

