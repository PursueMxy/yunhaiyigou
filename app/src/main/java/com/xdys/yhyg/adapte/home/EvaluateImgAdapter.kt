package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class EvaluateImgAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.evaluate_img) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.getView<ImageView>(R.id.ivEvaluate)
            .loadRoundCornerImage(R.mipmap.du_kang_jiu, 15)
    }
}
