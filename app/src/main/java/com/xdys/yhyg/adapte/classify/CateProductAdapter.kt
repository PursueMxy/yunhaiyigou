package com.xdys.yhyg.adapte.classify

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class CateProductAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_cate_product) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvGoodsName, "杏色娃娃领碎花雪纺连衣裙女夏季2021新款中长款...")
            .setText(R.id.tvPrice, "¥368.00")
            .setText(R.id.tvShopName, "杜康旗舰店")
            .setText(R.id.tvEvaluation, "500+条评价   98%好评")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu)
    }
}