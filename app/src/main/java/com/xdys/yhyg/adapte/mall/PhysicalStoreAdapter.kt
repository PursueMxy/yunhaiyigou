package com.xdys.yhyg.adapte.mall

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class PhysicalStoreAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_physical_store) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvSupermarket, "阳光超市（塔埔店）")
            .setText(R.id.tvBusinessHours, "营业时间：9:00~11:00")
            .setText(R.id.tvAddress, "厦门市思明区观音山塔埔路101-1店面")
            .getView<ImageView>(R.id.ivSupermarket).loadRoundCornerImage(R.mipmap.supermarket)

    }
}