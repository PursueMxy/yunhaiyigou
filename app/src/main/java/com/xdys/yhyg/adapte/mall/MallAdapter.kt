package com.xdys.yhyg.adapte.mall

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage

class MallAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_mall) {
    val shopLabelAdapter = ShopLabelAdapter()
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvShopName, "上官糖炒栗子·四果汤(塔埔店)")
            .setText(R.id.tvDistance, "533M")
            .setText(R.id.tvOpenTime, "营业时间：9:00~22:00")
            .setText(R.id.tvShopAddress, "厦门市思明区观音山塔埔路101-1店面")
            .getView<ImageView>(R.id.ivLogo).loadRoundCornerImage(R.mipmap.mall_logo)
        shopLabelAdapter.setNewInstance(mutableListOf("特色小吃", "好吃不贵", "美食餐饮"))
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        with(viewHolder.getView<RecyclerView>(R.id.rvLabel)) {
            adapter = shopLabelAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

    }
}
