package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadCircleImage

class SalesOrderAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_sales_order) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvGoodsName, "小猫爱吃鱼")
            .setText(R.id.tvSpecification, "香奈儿邂逅香水(瓶装)")
            .setText(R.id.tvTime, "2021-06-28  15:17")
            .setText(R.id.tvSum, "+￥858.9")
            .setText(R.id.tvStatus, "代付款")
            .getView<ImageView>(R.id.ivGoods).loadCircleImage(R.mipmap.schoolgirl)
    }
}
