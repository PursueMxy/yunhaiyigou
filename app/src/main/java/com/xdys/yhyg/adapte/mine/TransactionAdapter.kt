package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.entity.mine.TransactionEntity

class TransactionAdapter :
    BaseQuickAdapter<TransactionEntity, BaseViewHolder>(R.layout.item_transaction) {

    init {
        setDiffCallback(TransactionDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: TransactionEntity) {
        holder.setText(R.id.tvTitle, "购买成功通知")
            .setText(R.id.tvTime, "2021-06-15   14:11")
            .setText(R.id.tvContent, "尊敬的用户，您的16９９.00元退款已到账，钱款已退回至您的支付账户。")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu, 5)
    }
}

class TransactionDiffCallback : DiffUtil.ItemCallback<TransactionEntity>() {
    override fun areItemsTheSame(oldItem: TransactionEntity, newItem: TransactionEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TransactionEntity,
        newItem: TransactionEntity
    ): Boolean {
        return oldItem.title == newItem.title && oldItem.content == newItem.content &&
                oldItem.image == newItem.image && oldItem.time == newItem.time
    }
}