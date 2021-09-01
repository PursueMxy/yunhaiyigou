package com.xdys.yhyg.adapte.mine

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class DiscountCouponAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_announcement) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvTime, "10:33")
            .setText(R.id.tvTitle, "优惠券到期提醒！")
            .setText(R.id.tvContent, "尊敬的用户，您有一张优惠券即将到期，请尽快使用优惠券：500元优惠券")
    }
}