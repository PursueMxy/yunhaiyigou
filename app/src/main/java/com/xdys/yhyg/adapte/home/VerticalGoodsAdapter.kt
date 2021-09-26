package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.goods.GoodsEntity

class VerticalGoodsAdapter :
    BaseQuickAdapter<GoodsEntity, BaseViewHolder>(R.layout.item_vertical_goods) {

    init {
        setDiffCallback(VerticalGoodsDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: GoodsEntity) {
        holder.setText(R.id.tvGoodsName, "【酒厂直供】杜康秘藏1号 大容量口粮酒52度浓香型白酒...")
            .setText(R.id.tvPrice, "¥499")
            .setText(R.id.tvOriginalPrice, "¥899")
            .setText(R.id.tvSold, "已售30%")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu)
    }
}

class VerticalGoodsDiffCallback : DiffUtil.ItemCallback<GoodsEntity>() {
    override fun areItemsTheSame(
        oldItem: GoodsEntity,
        newItem: GoodsEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GoodsEntity,
        newItem: GoodsEntity
    ): Boolean {
        return oldItem.goodsName == newItem.goodsName && oldItem.price == newItem.price
                && oldItem.image == newItem.image && oldItem.sold == newItem.sold
    }
}