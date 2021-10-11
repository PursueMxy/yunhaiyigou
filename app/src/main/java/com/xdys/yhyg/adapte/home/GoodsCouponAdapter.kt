package com.xdys.yhyg.adapte.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.goods.GoodsEntity

class GoodsCouponAdapter :
    BaseQuickAdapter<GoodsEntity, BaseViewHolder>(R.layout.item_goods_coupon) {
    override fun convert(holder: BaseViewHolder, item: GoodsEntity) {
        holder.setText(R.id.tvPrice, "￥29.9")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu)
    }
}