package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.xdys.yhyg.R
import razerdp.basepopup.BasePopupWindow

class FeedbackPopupWindow(
    context: Context, private val confirm: () -> Unit,
) : BasePopupWindow(context) {

    val problemsAdapter =
        object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_feedback_problems) {
            override fun convert(holder: BaseViewHolder, item: String) {
                val tvProblems = holder.getView<TextView>(R.id.tvProblems)
                tvProblems.isSelected = holder.layoutPosition == 0
                tvProblems.text = item
            }
        }

    fun onCreateContentView(): View = createPopupById(R.layout.popup_feedback)
    override fun onViewCreated(contentView: View) {
        findViewById<TextView>(R.id.tvSubmitFeedback).setOnClickListener {
            confirm?.invoke()
            dismiss()
        }
        findViewById<ImageView>(R.id.ivClose).setOnClickListener {
            dismiss()
        }
    }

    fun setData(message: String): FeedbackPopupWindow {
        problemsAdapter.setNewInstance(mutableListOf("描述不清楚", "有错别字", "字太多，不想读", "其他"))
        findViewById<RecyclerView>(R.id.rvProblems).layoutManager = FlexboxLayoutManager(context)
        findViewById<RecyclerView>(R.id.rvProblems).adapter = problemsAdapter
        return this
    }

}