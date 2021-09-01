package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.NotificationEntity
import com.xdys.library.extension.loadRoundCornerImage

class NotificationAdapter :
    BaseQuickAdapter<NotificationEntity, BaseViewHolder>(R.layout.item_notification) {
    override fun convert(holder: BaseViewHolder, item: NotificationEntity) {
        holder.setText(R.id.tvType, item.name)
        holder.getView<ImageView>(R.id.ivMallActivities)
            .loadRoundCornerImage(item.map)
    }
}