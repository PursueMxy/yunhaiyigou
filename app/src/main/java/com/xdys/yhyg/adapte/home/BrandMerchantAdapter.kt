package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class BrandMerchantAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_brand_merchant) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvGoodsName, "居家生活仪式感")
            .setText(R.id.tvMerchant, "梦洁")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.imgthumb, 3)
        holder.getView<ImageView>(R.id.ivBusinessLogo).loadRoundCornerImage(R.mipmap.business_logo)
    }
}
