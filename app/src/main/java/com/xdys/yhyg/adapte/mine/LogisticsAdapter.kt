package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.LogisticsEntity

class LogisticsAdapter :
    BaseQuickAdapter<LogisticsEntity, BaseViewHolder>(R.layout.item_transaction) {
    override fun convert(holder: BaseViewHolder, item: LogisticsEntity) {
        holder.setText(R.id.tvTitle, "购买成功通知")
            .setText(R.id.tvCheckOrder, "查看取件信息")
            .setText(R.id.tvTime, "2021-06-15   14:11")
            .setText(R.id.tvContent, "您的商品正由圆通快递派送中请您注意查收验货，确认商品完好您的商品正由圆通快递派送中请您注意查收验货，确认商品完好。")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu, 5)
    }
}

class LogisticsDiffCallback : DiffUtil.ItemCallback<LogisticsEntity>() {
    override fun areItemsTheSame(oldItem: LogisticsEntity, newItem: LogisticsEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LogisticsEntity, newItem: LogisticsEntity): Boolean {
        return oldItem.content == newItem.content && oldItem.time == newItem.time
                && oldItem.checkOrder == newItem.checkOrder && oldItem.title == newItem.title
    }
}