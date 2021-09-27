package com.xdys.yhyg.adapte.order

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.order.OrderProductEntity

class OrderProductAdapter :
    BaseQuickAdapter<OrderProductEntity, BaseViewHolder>(R.layout.item_order_product) {


    override fun convert(holder: BaseViewHolder, item: OrderProductEntity) {
        holder.setText(R.id.tvGoodsName, "【酒厂直供】杜康秘藏1号 大容量口粮酒 52°浓香型白酒...")
            .setText(R.id.tvSpecification, "浓香型500ml/单瓶")
            .setText(R.id.tvPrice, "¥799.0")
            .setText(R.id.tvGoodsNum, "X1")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu, 7)
    }

}

//class OrderProductDiffCallback : DiffUtil.ItemCallback<OrderProductEntity>() {
//    override fun areItemsTheSame(
//        oldItem: OrderProductEntity,
//        newItem: OrderProductEntity
//    ): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(
//        oldItem: OrderProductEntity,
//        newItem: OrderProductEntity
//    ): Boolean {
//        return oldItem.goodsName == oldItem.goodsName && oldItem.goodsNum == newItem.goodsNum &&
//                oldItem.price == newItem.price && oldItem.specification == newItem.specification
//    }
//}