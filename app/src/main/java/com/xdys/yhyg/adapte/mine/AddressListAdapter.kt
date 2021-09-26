package com.xdys.yhyg.adapte.mine

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.AddressEntity

class AddressListAdapter :
    BaseQuickAdapter<AddressEntity, BaseViewHolder>(R.layout.item_address_list) {

    init {
        setDiffCallback(AddressDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: AddressEntity) {
        holder.setText(R.id.tvRecipient, "张富贵")
            .setText(R.id.tvMobile, "178****8888")
            .setText(R.id.tvAddress, "福建厦门市思明区城区莲前街道西林西里25号楼2层205室")
        holder.getView<TextView>(R.id.tvDefaultAddress).setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }
}

class AddressDiffCallback : DiffUtil.ItemCallback<AddressEntity>() {
    override fun areItemsTheSame(oldItem: AddressEntity, newItem: AddressEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AddressEntity, newItem: AddressEntity): Boolean {
        return oldItem.address == newItem.address && oldItem.province == newItem.province &&
                oldItem.city == newItem.city && oldItem.area == newItem.area
    }

}