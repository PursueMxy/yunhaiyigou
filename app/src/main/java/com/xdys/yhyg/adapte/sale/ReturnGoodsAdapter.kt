package com.xdys.yhyg.adapte.sale

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.order.OrderProductAdapter

class ReturnGoodsAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_return_goods) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvName, "酒祖杜康12窖区 窖龄40年 50度浓香型白酒500ml单瓶酒盒装...")
            .setText(R.id.tvGoodsType, "原窖56°500ml/瓶")
            .setText(R.id.tvNum, "x1")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu)
    }


}