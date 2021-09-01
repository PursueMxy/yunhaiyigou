package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class ShopCollectionAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_shop_collection) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvShopName, "戈蔓婷女装专卖店")
            .setText(R.id.tvAttention, "1.2万人关注")
            .getView<ImageView>(R.id.ivShops).loadRoundCornerImage(R.mipmap.mall_logo)
    }
}