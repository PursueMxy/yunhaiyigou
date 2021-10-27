package com.xdys.yhyg.adapte.goods

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.currency
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.goods.OrderGoods

class OrderGoodsAdapter : BaseQuickAdapter<OrderGoods, BaseViewHolder>(R.layout.item_order_goods) {
    override fun convert(holder: BaseViewHolder, item: OrderGoods) {
        holder.setText(R.id.tvGoodsName, item.goodsName)
            .setText(R.id.tvSpecs, item.specValueName)
            .setText(R.id.tvPrice, item.price?.currency())
            .setText(R.id.tvNumber, "X${item.quantity}")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(item.url)
    }
}