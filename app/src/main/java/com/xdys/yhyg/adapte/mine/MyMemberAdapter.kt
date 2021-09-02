package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadCircleImage
import com.xdys.yhyg.R

class MyMemberAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_my_member) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvUserName, "Jun Myers")
            .setText(R.id.tvUserId, "（ID:2011022）")
            .setText(R.id.tvMemberType, "普通会员")
            .getView<ImageView>(R.id.ivAvatar).loadCircleImage(R.mipmap.schoolgirl)
    }
}