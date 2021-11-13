package com.xdys.yhyg.adapte.order

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.currency
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.order.GoodsEntity

class OrderProductAdapter :
    BaseQuickAdapter<GoodsEntity, BaseViewHolder>(R.layout.item_order_product) {

    override fun convert(holder: BaseViewHolder, item: GoodsEntity) {
        holder.setText(R.id.tvGoodsName, item.spuName)
            .setText(R.id.tvSpecification, item.specInfo)
            .setText(R.id.tvPrice, item.salesPrice?.currency())
            .setText(R.id.tvGoodsNum, "X${item.quantity}")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu, 7)
    }

}
