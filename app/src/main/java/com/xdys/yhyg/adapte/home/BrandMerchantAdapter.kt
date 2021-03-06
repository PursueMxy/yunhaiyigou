package com.xdys.yhyg.adapte.home

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.home.BrandMerchantEntity

class BrandMerchantAdapter :
    BaseQuickAdapter<BrandMerchantEntity, BaseViewHolder>(R.layout.item_brand_merchant) {

    init {
        setDiffCallback(BrandMerchantDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: BrandMerchantEntity) {
        holder.setText(R.id.tvGoodsName, item.name)
            .setText(R.id.tvMerchant, item.detail)
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(item.imgUrl, 3 , R.mipmap.imgthumb)
        holder.getView<ImageView>(R.id.ivBusinessLogo).loadRoundCornerImage(R.mipmap.business_logo)
    }
}


class BrandMerchantDiffCallback : DiffUtil.ItemCallback<BrandMerchantEntity>() {
    override fun areItemsTheSame(
        oldItem: BrandMerchantEntity,
        newItem: BrandMerchantEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: BrandMerchantEntity,
        newItem: BrandMerchantEntity
    ): Boolean {
        return oldItem.name == newItem.name && oldItem.imgUrl == newItem.imgUrl
                && oldItem.detail == newItem.detail
    }
}
