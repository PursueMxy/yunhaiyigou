package com.xdys.yhyg.adapte.mine

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class DevicesAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_devices) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvDevicesName,"华为mate40Pro")
            .setText(R.id.tvTime,"星期三  22:55")

    }
}