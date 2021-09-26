package com.xdys.yhyg.adapte.mine

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.DiscountCoupon

class DiscountCouponAdapter :
    BaseQuickAdapter<DiscountCoupon, BaseViewHolder>(R.layout.item_announcement) {

    init {
        setDiffCallback(DiscountCouponDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: DiscountCoupon) {
        holder.setText(R.id.tvTime, "10:33")
            .setText(R.id.tvTitle, "优惠券到期提醒！")
            .setText(R.id.tvContent, "尊敬的用户，您有一张优惠券即将到期，请尽快使用优惠券：500元优惠券")
    }
}

class DiscountCouponDiffCallback : DiffUtil.ItemCallback<DiscountCoupon>() {
    override fun areItemsTheSame(oldItem: DiscountCoupon, newItem: DiscountCoupon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DiscountCoupon, newItem: DiscountCoupon): Boolean {
        return oldItem.content == newItem.content && oldItem.title == oldItem.title
    }
}