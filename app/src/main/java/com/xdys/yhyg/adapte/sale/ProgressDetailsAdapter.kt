package com.xdys.yhyg.adapte.sale

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class ProgressDetailsAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_progress_detail) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvStatus,"退款申请售后审核通过")
            .setText(R.id.tvContent,"您的服务单号422485754已退款，请注意查收")
            .setText(R.id.tvTime,"2021-05-22  09:33:11")

    }
}