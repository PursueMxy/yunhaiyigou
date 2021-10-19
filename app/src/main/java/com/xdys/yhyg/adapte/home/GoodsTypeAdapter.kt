package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.home.SecCatEntity

class GoodsTypeAdapter :
    BaseQuickAdapter<SecCatEntity, BaseViewHolder>(R.layout.item_goods_type) {
    override fun convert(holder: BaseViewHolder, item: SecCatEntity) {
        holder.setText(R.id.tvTypeName, item.name)
            .getView<ImageView>(R.id.ivType).loadRoundCornerImage(item.picUrl, 3)
    }
}