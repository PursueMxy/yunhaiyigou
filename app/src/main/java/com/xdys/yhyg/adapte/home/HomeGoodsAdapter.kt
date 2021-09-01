package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class HomeGoodsAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_home_goods) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvGoodsName, "丹麦格兰富水泵2别墅家用自动变频机丹麦格兰富水泵2别墅家用自动变频机")
            .setText(R.id.tvPrice, "¥33999")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.watch, 3)
    }
}