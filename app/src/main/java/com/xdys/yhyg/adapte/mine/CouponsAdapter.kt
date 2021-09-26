package com.xdys.yhyg.adapte.mine

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.CouponsEntity

class CouponsAdapter :
    BaseQuickAdapter<CouponsEntity, BaseViewHolder>(R.layout.item_coupons) {

    init {
        setDiffCallback(CouponsDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: CouponsEntity) {
        holder.setText(R.id.tvCouponsPrice, "100")
            .setText(R.id.tvCouponConditions, "满9999可用")
            .setText(R.id.tvCouponsType, "全品类优惠券")
            .setText(R.id.tvTitle, "全品类优惠券")
            .setText(R.id.tvTime, "2021.06.01-2021.06.30")
        val clCoupons = holder.getView<ConstraintLayout>(R.id.clCoupons)
        when (item.type) {
            "1" -> clCoupons.background =
                ResourcesCompat.getDrawable(context.resources, R.mipmap.coupon_used, null)
            "2" -> clCoupons.background =
                ResourcesCompat.getDrawable(context.resources, R.mipmap.unused_coupon, null)
            else -> clCoupons.background =
                ResourcesCompat.getDrawable(context.resources, R.mipmap.shop_coupons, null)
        }
    }
}

class CouponsDiffCallback : DiffUtil.ItemCallback<CouponsEntity>() {
    override fun areItemsTheSame(oldItem: CouponsEntity, newItem: CouponsEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CouponsEntity, newItem: CouponsEntity): Boolean {
        return oldItem.CouponConditions == newItem.CouponConditions && oldItem.CouponsPrice == newItem.CouponsPrice
                && oldItem.CouponsType == oldItem.CouponsType && oldItem.time == oldItem.time &&
                oldItem.title == oldItem.title
    }
}
