package com.xdys.yhyg.adapte.mine

import androidx.constraintlayout.widget.ConstraintLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class CouponsAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_coupons) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvCouponsPrice, "100")
            .setText(R.id.tvCouponConditions, "满9999可用")
            .setText(R.id.tvCouponsType, "全品类优惠券")
            .setText(R.id.tvTitle, "全品类优惠券")
            .setText(R.id.tvTime, "2021.06.01-2021.06.30")
        val clCoupons = holder.getView<ConstraintLayout>(R.id.clCoupons)
        when (item) {
            "1" -> clCoupons.background = context.resources.getDrawable(R.mipmap.coupon_used)
            "2" -> clCoupons.background = context.resources.getDrawable(R.mipmap.unused_coupon)
            else -> clCoupons.background = context.resources.getDrawable(R.mipmap.shop_coupons)
        }
    }
}
