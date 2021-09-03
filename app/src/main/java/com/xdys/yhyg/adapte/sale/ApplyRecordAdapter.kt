package com.xdys.yhyg.adapte.sale

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R

class ApplyRecordAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_apply_record) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvName, "酒祖杜康12窖区 窖龄40年 50度浓香型白酒500ml单瓶酒盒装...")
            .setText(R.id.tvNumberApplications, "申请数量：1")
            .setText(R.id.tvStatus, "退货")
            .setText(R.id.tvAuditStatus, "审核中")
            .setText(R.id.tvNumber,"服务单号：422485754")
            .setText(R.id.tvAuditContent, "您的服务单已经申请成功，待售后审核")
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu)
    }
}
