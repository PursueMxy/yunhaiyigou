package com.xdys.yhyg.adapte.mine

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class IncomeBreakdownAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_points_detail) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvTitle, "购物积分抵扣")
            .setText(R.id.tvTime, "2021-08-05  10:34")
            .setText(R.id.tvNumber, "+300")
    }
}
