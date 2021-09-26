package com.xdys.yhyg.adapte.mall

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mall.PhysicalStore

class PhysicalStoreAdapter :
    BaseQuickAdapter<PhysicalStore, BaseViewHolder>(R.layout.item_physical_store) {

    init {
        setDiffCallback(PhysicalStoreDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: PhysicalStore) {
        holder.setText(R.id.tvSupermarket, item.supermarket)
            .setText(R.id.tvBusinessHours, "营业时间：${item.businessHours}")
            .setText(R.id.tvAddress, item.address)
            .getView<ImageView>(R.id.ivSupermarket).loadRoundCornerImage(R.mipmap.supermarket)
    }
}


class PhysicalStoreDiffCallback : DiffUtil.ItemCallback<PhysicalStore>() {
    override fun areItemsTheSame(
        oldItem: PhysicalStore,
        newItem: PhysicalStore
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PhysicalStore,
        newItem: PhysicalStore
    ): Boolean {
        return oldItem.supermarket == newItem.supermarket && oldItem.businessHours == newItem.businessHours
                && oldItem.address == newItem.address
    }
}

