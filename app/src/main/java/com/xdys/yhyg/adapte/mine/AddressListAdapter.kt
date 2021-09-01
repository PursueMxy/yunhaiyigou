package com.xdys.yhyg.adapte.mine

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class AddressListAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_address_list) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvRecipient, "张富贵")
            .setText(R.id.tvMobile, "178****8888")
            .setText(R.id.tvAddress, "福建厦门市思明区城区莲前街道西林西里25号楼2层205室")
        holder.getView<TextView>(R.id.tvDefaultAddress).setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }
}