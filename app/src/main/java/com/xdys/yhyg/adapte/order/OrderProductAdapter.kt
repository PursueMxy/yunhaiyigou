package com.xdys.yhyg.adapte.order

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.currency
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.order.GoodsEntity
import com.xdys.yhyg.entity.order.OrderProductEntity

class OrderProductAdapter :
    BaseQuickAdapter<GoodsEntity, BaseViewHolder>(R.layout.item_order_product) {


    override fun convert(holder: BaseViewHolder, item: GoodsEntity) {
        holder.setText(R.id.tvGoodsName, item.goodsName)
            .setText(R.id.tvSpecification, item.goodsSpecifications)
            .setText(R.id.tvPrice, item.goodsPrice?.currency())
            .setText(R.id.tvGoodsNum, "X${item.goodsNumber}")
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