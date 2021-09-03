package com.xdys.yhyg.adapte.sale

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class IssueAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_issue) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvIssue, "我不想退货了能让重新...")
    }
}