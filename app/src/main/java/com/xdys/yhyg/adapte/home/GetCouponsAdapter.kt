package com.xdys.yhyg.adapte.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class GetCouponsAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_get_coupons) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvGetCoupon, item)
    }
}