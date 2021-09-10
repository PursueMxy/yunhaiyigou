package com.xdys.yhyg.adapte.classify

import android.widget.ImageView
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.classify.CategoriesBanner
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class BannerImgAdapter : BaseBannerAdapter<CategoriesBanner>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.home_banner_img
    }

    override fun bindData(
        holder: BaseViewHolder<CategoriesBanner>,
        data: CategoriesBanner?,
        position: Int,
        pageSize: Int
    ) {
        holder.findViewById<ImageView>(R.id.banner).loadRoundCornerImage(data?.pic, 10)
    }
}