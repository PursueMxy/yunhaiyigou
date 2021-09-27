package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadCircleImage
import com.xdys.yhyg.entity.mine.SalesOrderEntity

class SalesOrderAdapter :
    BaseQuickAdapter<SalesOrderEntity, BaseViewHolder>(R.layout.item_sales_order) {

    init {
        setDiffCallback(SalesOrderDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: SalesOrderEntity) {
        holder.setText(R.id.tvGoodsName, "小猫爱吃鱼")
            .setText(R.id.tvSpecification, "香奈儿邂逅香水(瓶装)")
            .setText(R.id.tvTime, "2021-06-28  15:17")
            .setText(R.id.tvSum, "+￥858.9")
            .setText(R.id.tvStatus, "代付款")
            .getView<ImageView>(R.id.ivGoods).loadCircleImage(R.mipmap.schoolgirl)
    }
}

class SalesOrderDiffCallback : DiffUtil.ItemCallback<SalesOrderEntity>() {
    override fun areItemsTheSame(oldItem: SalesOrderEntity, newItem: SalesOrderEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SalesOrderEntity, newItem: SalesOrderEntity): Boolean {
        return oldItem.goodsName == newItem.goodsName && oldItem.status == newItem.status
                && oldItem.image == newItem.image && oldItem.time == newItem.time
    }
}
