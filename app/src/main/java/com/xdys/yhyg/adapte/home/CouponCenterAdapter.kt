package com.xdys.yhyg.adapte.home

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.goods.GoodsEntity
import com.xdys.yhyg.entity.home.CouponCenterEntity

class CouponCenterAdapter :
    BaseQuickAdapter<CouponCenterEntity, CouponCenterViewHolder>(R.layout.item_coupon_center) {

    init {
        setDiffCallback(CouponCenterDiffCallback())
    }

    override fun convert(holder: CouponCenterViewHolder, item: CouponCenterEntity) {
        holder.setText(R.id.tvShop, "仅限购买食品饮料部分商品")
            .setText(R.id.tvDiscount, "8折")
            .setText(R.id.tvConditions, "满199元可用")
            .setText(R.id.btnOperation, "立即领取")
        holder.getView<View>(R.id.btnOperation).isSelected = holder.layoutPosition == 1
        holder.goodsAdapter.setNewInstance(
            mutableListOf(
                GoodsEntity(),
                GoodsEntity(),
                GoodsEntity()
            )
        )

    }

    override fun onItemViewHolderCreated(viewHolder: CouponCenterViewHolder, viewType: Int) {
        with(viewHolder.getView<RecyclerView>(R.id.rvGoods)) {
            adapter = viewHolder.goodsAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
    }
}

class CouponCenterViewHolder(view: View) : BaseViewHolder(view) {
    val goodsAdapter = GoodsCouponAdapter()
}


class CouponCenterDiffCallback : DiffUtil.ItemCallback<CouponCenterEntity>() {
    override fun areItemsTheSame(
        oldItem: CouponCenterEntity,
        newItem: CouponCenterEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CouponCenterEntity,
        newItem: CouponCenterEntity
    ): Boolean {
        return oldItem.price == newItem.price
    }

}

