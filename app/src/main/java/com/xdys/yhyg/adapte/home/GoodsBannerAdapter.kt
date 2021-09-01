package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class GoodsBannerAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.goods_banner) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.getView<ImageView>(R.id.banner)
            .loadRoundCornerImage(item)
    }
}