package com.xdys.yhyg.adapte.mine

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class AnnouncementAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_announcement) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvTime, "10:33")
            .setText(R.id.tvTitle, "618超级优惠，活动狂欢开启！")
            .setText(R.id.tvContent, "杜康古城酒业商城全场满减优惠，下单就送好礼品，手慢无，杜康古城酒业商城全场满减优惠，下单就送好礼品...o")
    }
}