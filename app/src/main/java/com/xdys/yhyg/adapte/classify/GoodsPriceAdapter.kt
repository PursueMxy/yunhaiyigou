package com.xdys.yhyg.adapte.classify

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class GoodsPriceAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.cate_goods_item) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvPrice, "¥899")
            .getView<ImageView>(R.id.ivGoods)
            .loadRoundCornerImage(R.mipmap.du_kang_jiu, 8)
    }
}