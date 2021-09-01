package com.xdys.yhyg.adapte.mall

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class ShopLabelAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_shop_label) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvLabel, item)
    }
}
