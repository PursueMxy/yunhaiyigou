package com.xdys.yhyg.adapte.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class SearchFoundAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_found_list) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvContent, item)

    }
}
