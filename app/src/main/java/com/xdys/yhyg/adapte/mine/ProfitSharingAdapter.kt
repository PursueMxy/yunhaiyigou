package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadCircleImage
import com.xdys.yhyg.R

class ProfitSharingAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_profit_sharing) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvTitle, "云网点推广收益")
            .setText(R.id.tvTime, "2021-08-05  10:34")
            .setText(R.id.tvIncome, "+300")
    }
}