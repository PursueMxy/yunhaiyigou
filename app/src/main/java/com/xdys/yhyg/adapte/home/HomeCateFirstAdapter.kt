package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadCircleImage
import com.xdys.yhyg.entity.home.ButtonList


class HomeCateFirstAdapter : BaseQuickAdapter<ButtonList, BaseViewHolder>(R.layout.item_child) {
    override fun convert(holder: BaseViewHolder, item: ButtonList) {
        holder.setText(R.id.tvCateName, item.text)
            .getView<ImageView>(R.id.ivCateCover).loadCircleImage(item.icon,R.mipmap.new_exclusive)
    }
}