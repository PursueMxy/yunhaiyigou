package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadCircleImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.MemberEntity

class MyMemberAdapter :
    BaseQuickAdapter<MemberEntity, BaseViewHolder>(R.layout.item_my_member) {

    init {
        setDiffCallback(MemberDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: MemberEntity) {
        holder.setText(R.id.tvUserName, "Jun Myers")
            .setText(R.id.tvUserId, "（ID:2011022）")
            .setText(R.id.tvMemberType, "普通会员")
            .getView<ImageView>(R.id.ivAvatar).loadCircleImage(R.mipmap.schoolgirl)
    }
}

class MemberDiffCallback : DiffUtil.ItemCallback<MemberEntity>() {
    override fun areItemsTheSame(oldItem: MemberEntity, newItem: MemberEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MemberEntity, newItem: MemberEntity): Boolean {
        return oldItem.userName == newItem.userName && oldItem.image == newItem.image &&
                oldItem.memberType == newItem.memberType && oldItem.userId == oldItem.userId
    }
}