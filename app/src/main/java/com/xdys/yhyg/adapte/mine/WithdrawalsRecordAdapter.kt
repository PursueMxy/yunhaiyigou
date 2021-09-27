package com.xdys.yhyg.adapte.mine

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.WithdrawalsRecordEntity

class WithdrawalsRecordAdapter :
    BaseQuickAdapter<WithdrawalsRecordEntity, BaseViewHolder>(R.layout.item_withdrawals_record) {

    init {
        setDiffCallback(WithdrawalsRecordDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: WithdrawalsRecordEntity) {
        holder.setText(R.id.tvTitle, "购物积分抵扣")
            .setText(R.id.tvTime, "2021-08-05  10:34")
            .setText(R.id.tvNumber, "+300")
    }
}

class WithdrawalsRecordDiffCallback : DiffUtil.ItemCallback<WithdrawalsRecordEntity>() {
    override fun areItemsTheSame(
        oldItem: WithdrawalsRecordEntity,
        newItem: WithdrawalsRecordEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WithdrawalsRecordEntity,
        newItem: WithdrawalsRecordEntity
    ): Boolean {
        return oldItem.title == newItem.title && oldItem.time == newItem.time
                && oldItem.number == newItem.number
    }
}
