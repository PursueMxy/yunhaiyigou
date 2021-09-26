package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.ActivitiesEntity

class ActivitiesAdapter :
    BaseQuickAdapter<ActivitiesEntity, BaseViewHolder>(R.layout.item_activities) {

    init {
        setDiffCallback(ActivitiesDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: ActivitiesEntity) {
        holder.setText(R.id.tvTime, "10:23")
            .setText(R.id.tvTitle, "618超级优惠，全场满1000减500活动狂欢开启！")
            .setText(R.id.tvContent, "杜康古城酒业商城全场满减优惠，下单就送好礼品，手慢无，立即下单>>")
            .getView<ImageView>(R.id.ivLeaflet).loadRoundCornerImage(R.mipmap.good_products)
    }
}

class ActivitiesDiffCallback : DiffUtil.ItemCallback<ActivitiesEntity>() {
    override fun areItemsTheSame(oldItem: ActivitiesEntity, newItem: ActivitiesEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ActivitiesEntity, newItem: ActivitiesEntity): Boolean {
        return oldItem.time == newItem.time && oldItem.title == newItem.title
                && oldItem.content == newItem.content && oldItem.leaflet == newItem.leaflet
    }
}