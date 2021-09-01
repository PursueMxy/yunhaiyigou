package com.xdys.yhyg.adapte.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class GuaranteeAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_guarantee) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvTick, item)
    }
}