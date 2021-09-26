package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.goods.GoodsEntity

class HomeGoodsAdapter :
    BaseQuickAdapter<GoodsEntity, BaseViewHolder>(R.layout.item_home_goods) {

    init {
        setDiffCallback(HomeGoodsDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: GoodsEntity) {
        holder.setText(R.id.tvGoodsName, "丹麦格兰富水泵2别墅家用自动变频机丹麦格兰富水泵2别墅家用自动变频机")
            .setText(R.id.tvPrice, "¥33999")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.watch, 3)
    }
}

class HomeGoodsDiffCallback : DiffUtil.ItemCallback<GoodsEntity>() {
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
                && oldItem.image == newItem.image
    }
}