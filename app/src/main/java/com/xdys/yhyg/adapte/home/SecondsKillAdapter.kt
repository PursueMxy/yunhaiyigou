package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R

class SecondsKillAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_seconds_kill) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvGoodsName, "【酒厂直供】杜康秘藏1号 大容量口粮酒52度浓香型白酒...")
            .setText(R.id.tvPrice, "¥499")
            .setText(R.id.tvOriginalPrice, "¥899")
            .setText(R.id.tvSold, "已售30%")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu)
    }
}