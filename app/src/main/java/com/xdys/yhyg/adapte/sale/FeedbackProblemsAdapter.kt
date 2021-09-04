package com.xdys.yhyg.adapte.sale

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class FeedbackProblemsAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_feedback_problems) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.getView<TextView>(R.id.tvProblems).isSelected = holder.layoutPosition == 0

    }
}