package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class CommodityCollectionAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_commodity_collection) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvGoodsName, "【酒厂直供】杜康秘藏1号 大容量口粮酒 52度浓香型白酒...")
            .setText(R.id.tvPrice, "¥899")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu)

    }
}
