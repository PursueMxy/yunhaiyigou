package com.xdys.yhyg.adapte.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.goods.EnsureByEntity

class GuaranteeAdapter : BaseQuickAdapter<EnsureByEntity, BaseViewHolder>(R.layout.item_guarantee) {
    override fun convert(holder: BaseViewHolder, item: EnsureByEntity) {
        holder.setText(R.id.tvTick, item.name)
    }
}