package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadCircleImage


class HomeCateFirstAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_child) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvCateName, item)
            .getView<ImageView>(R.id.ivCateCover).loadCircleImage(R.mipmap.new_exclusive)
    }
}