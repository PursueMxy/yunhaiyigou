package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.home.BannerBean

class ImageAdapter : BaseQuickAdapter<BannerBean, BaseViewHolder>(R.layout.home_banner) {
    override fun convert(holder: BaseViewHolder, item: BannerBean) {
        holder.getView<ImageView>(R.id.banner)
            .loadRoundCornerImage(item.imgUrl, 15)
    }
}