package com.xdys.yhyg.adapte.mine

import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.DevicesEntity

class DevicesAdapter :
    BaseQuickAdapter<DevicesEntity, BaseViewHolder>(R.layout.item_devices) {

    init {
        setDiffCallback(DevicesDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: DevicesEntity) {
        holder.setText(R.id.tvDevicesName, "华为mate40Pro")
            .setText(R.id.tvTime, "星期三  22:55")

    }
}

class DevicesDiffCallback : DiffUtil.ItemCallback<DevicesEntity>() {
    override fun areItemsTheSame(oldItem: DevicesEntity, newItem: DevicesEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DevicesEntity, newItem: DevicesEntity): Boolean {
        return oldItem.time == newItem.time && oldItem.devicesName == newItem.devicesName
    }
}

