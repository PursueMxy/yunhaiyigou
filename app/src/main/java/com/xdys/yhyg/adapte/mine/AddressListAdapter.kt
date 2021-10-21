package com.xdys.yhyg.adapte.mine

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.address.AddressEntity

class AddressListAdapter :
    BaseQuickAdapter<AddressEntity, BaseViewHolder>(R.layout.item_address_list) {

    init {
        setDiffCallback(AddressDiffCallback())
        addChildClickViewIds(R.id.tvRedact, R.id.tvDelete, R.id.tvDefaultAddress)
    }

    override fun convert(holder: BaseViewHolder, item: AddressEntity) {
        holder.setText(R.id.tvRecipient, item.consigneeName)
            .setText(R.id.tvMobile, item.phone)
            .setText(
                R.id.tvAddress,
                "${item.provinceName}${item.cityName}${item.townsName}${item.detailedAddress}"
            )
            .getView<TextView>(R.id.tvDefaultAddress).isSelected = item.hasDefault == "1"
    }
}

class AddressDiffCallback : DiffUtil.ItemCallback<AddressEntity>() {
    override fun areItemsTheSame(oldItem: AddressEntity, newItem: AddressEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AddressEntity, newItem: AddressEntity): Boolean {
        return oldItem.detailedAddress == newItem.detailedAddress && oldItem.province == newItem.province &&
                oldItem.city == newItem.city && oldItem.cityName == newItem.cityName && oldItem.towns == newItem.towns
                && oldItem.townsName == newItem.townsName && oldItem.hasDefault == newItem.hasDefault
    }

}