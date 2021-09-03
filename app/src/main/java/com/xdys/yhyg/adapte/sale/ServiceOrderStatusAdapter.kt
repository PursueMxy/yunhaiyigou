package com.xdys.yhyg.adapte.sale

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class ServiceOrderStatusAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_service_order_status) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvStatus,"提交申请")

    }
}