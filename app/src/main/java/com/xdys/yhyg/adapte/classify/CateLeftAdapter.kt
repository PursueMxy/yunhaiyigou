package com.xdys.yhyg.adapte.classify

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.classify.CateEntity

class CateLeftAdapter(
    private val onCateSelect: ((Int) -> Unit)? = null
) : BaseQuickAdapter<CateEntity, BaseViewHolder>(R.layout.item_cate_left) {

    private var selectedPosition = 0

//    init {
//        setOnItemClickListener { _, _, position -> scrollToPosition(position) }
//    }

    override fun convert(holder: BaseViewHolder, item: CateEntity) = with(holder) {
        setText(R.id.tvCateName, item.name)
        getView<TextView>(R.id.tvCateName).paint.isFakeBoldText =
            selectedPosition == holder.adapterPosition
        itemView.isSelected = selectedPosition == holder.adapterPosition
    }

    // 将选中的item滚动到屏幕中间
    fun scrollToPosition(position: Int) {
        recyclerView.smoothScrollToPosition(position)
        // 触发回调
        onCateSelect?.invoke(position)
        // 切换选中的Item
        selectedPosition = position
        notifyDataSetChanged()
//        recyclerView.postDelayed({
//            (recyclerView.layoutManager as? LinearLayoutManager)?.run {
//                findViewByPosition(position)?.let {
//                 `   val centerTop = (recyclerView.measuredHeight - it.height) / 2
//                    if (it.top != centerTop) recyclerView.smoothScrollBy(0, it.top - centerTop)
//                }
//            }
//            // 触发回调
//            onCateSelect?.invoke(position)
//            // 切换选中的Item
//            selectedPosition = position
//            notifyDataSetChanged()
//        }, 300L)
    }
}