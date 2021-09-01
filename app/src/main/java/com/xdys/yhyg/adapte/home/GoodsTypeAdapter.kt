package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class GoodsTypeAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_goods_type) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvTypeName, item)
            .getView<ImageView>(R.id.ivType).loadRoundCornerImage(R.mipmap.watch, 3)
    }
}