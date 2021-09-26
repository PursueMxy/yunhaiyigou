package com.xdys.yhyg.adapte.mall

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mall.MallEntity

class MallAdapter :
    BaseQuickAdapter<MallEntity, BaseViewHolder>(R.layout.item_mall) {

    init {
        setDiffCallback(VerticalGoodsDiffCallback())
    }

    val shopLabelAdapter = ShopLabelAdapter()
    override fun convert(holder: BaseViewHolder, item: MallEntity) {
        holder.setText(R.id.tvShopName, item.shopName)
            .setText(R.id.tvDistance, item.distance)
            .setText(R.id.tvOpenTime, item.openTime)
            .setText(R.id.tvShopAddress, item.address)
            .getView<ImageView>(R.id.ivLogo).loadRoundCornerImage(item.logo)
        shopLabelAdapter.setNewInstance(item.labelList)
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        with(viewHolder.getView<RecyclerView>(R.id.rvLabel)) {
            adapter = shopLabelAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

    }
}

class VerticalGoodsDiffCallback : DiffUtil.ItemCallback<MallEntity>() {
    override fun areItemsTheSame(
        oldItem: MallEntity,
        newItem: MallEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MallEntity,
        newItem: MallEntity
    ): Boolean {
        return oldItem.shopName == newItem.shopName && oldItem.openTime == newItem.openTime
                && oldItem.address == newItem.address && oldItem.shopName == newItem.distance
    }
}
