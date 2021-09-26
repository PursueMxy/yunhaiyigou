package com.xdys.yhyg.adapte.mine

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.AnnouncementEntity

class AnnouncementAdapter :
    BaseQuickAdapter<AnnouncementEntity, BaseViewHolder>(R.layout.item_announcement) {

    init {
        setDiffCallback(AnnouncementDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: AnnouncementEntity) {
        holder.setText(R.id.tvTime, "10:33")
            .setText(R.id.tvTitle, "618超级优惠，活动狂欢开启！")
            .setText(R.id.tvContent, "杜康古城酒业商城全场满减优惠，下单就送好礼品，手慢无，杜康古城酒业商城全场满减优惠，下单就送好礼品...o")
    }
}

class AnnouncementDiffCallback : DiffUtil.ItemCallback<AnnouncementEntity>() {
    override fun areItemsTheSame(
        oldItem: AnnouncementEntity,
        newItem: AnnouncementEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AnnouncementEntity,
        newItem: AnnouncementEntity
    ): Boolean {
        return oldItem.title == newItem.title && oldItem.content == newItem.content
                && oldItem.time == oldItem.time
    }
}