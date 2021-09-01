package com.xdys.yhyg.adapte.mine

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.FeedbackEntity

class FeedbackAdapter :
    BaseQuickAdapter<FeedbackEntity, BaseViewHolder>(R.layout.item_feedback) {
    override fun convert(holder: BaseViewHolder, item: FeedbackEntity) {
        holder.setText(R.id.tvTitle, item.title)
        holder.getView<ImageView>(R.id.ivHide).isSelected = item.isHide
        holder.getView<View>(R.id.flHidden).setOnClickListener {
            item.isHide = !item.isHide
            this@FeedbackAdapter.notifyDataSetChanged()
        }
        holder.getView<TextView>(R.id.tvContent).isVisible = if (item.isHide) true else false

    }
}