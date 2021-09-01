package com.xdys.yhyg.adapte.order

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class OrderProductAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_order_product) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvGoodsName, "【酒厂直供】杜康秘藏1号 大容量口粮酒 52°浓香型白酒...")
            .setText(R.id.tvSpecification, "浓香型500ml/单瓶")
            .setText(R.id.tvPrice, "¥799.0")
            .setText(R.id.tvGoodsNum, "X1")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu, 7)
    }
}