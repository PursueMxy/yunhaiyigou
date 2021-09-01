package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class BrandSelectionAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_brand_selection) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.getView<ImageView>(R.id.ivLogo).loadRoundCornerImage(R.mipmap.business_logo)

    }
}