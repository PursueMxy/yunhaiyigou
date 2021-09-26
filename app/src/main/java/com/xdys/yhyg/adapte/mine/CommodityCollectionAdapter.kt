package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.CommodityCollection

class CommodityCollectionAdapter :
    BaseQuickAdapter<CommodityCollection, BaseViewHolder>(R.layout.item_commodity_collection) {

    init {
        setDiffCallback(CommodityCollectionDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: CommodityCollection) {
        holder.setText(R.id.tvGoodsName, "【酒厂直供】杜康秘藏1号 大容量口粮酒 52度浓香型白酒...")
            .setText(R.id.tvPrice, "¥899")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu)

    }
}

class CommodityCollectionDiffCallback : DiffUtil.ItemCallback<CommodityCollection>() {
    override fun areItemsTheSame(
        oldItem: CommodityCollection,
        newItem: CommodityCollection
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CommodityCollection,
        newItem: CommodityCollection
    ): Boolean {
        return oldItem.goodsName == newItem.goodsName && oldItem.price == oldItem.price
                && oldItem.image == oldItem.image
    }
}
