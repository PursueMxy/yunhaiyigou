package com.xdys.yhyg.adapte.goods

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class EvaluateTypeAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_evaluation_type) {

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvContent, item)
    }

}
