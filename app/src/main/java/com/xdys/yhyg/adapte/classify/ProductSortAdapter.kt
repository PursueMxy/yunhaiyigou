package com.xdys.yhyg.adapte.classify

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.classify.SortEntity

class ProductSortAdapter(
    private val onSortSelected: ((SortEntity) -> Unit)? = null
) : BaseQuickAdapter<SortEntity, BaseViewHolder>(R.layout.item_product_sort) {

    private var selectedPosition = 0
    var currentSort: SortEntity? = null

    init {
        addChildClickViewIds(R.id.area)
        setOnItemChildClickListener { _, view, position ->
            if (view.id == R.id.area && sort(position)) {
                currentSort = data[position]
                notifyDataSetChanged()
                onSortSelected?.invoke(data[position])
            }
        }
    }

    override fun convert(holder: BaseViewHolder, item: SortEntity): Unit = with(holder) {
        setText(R.id.tvSortName, item.name)
        setGone(R.id.ivSort, !item.canSort)
//        setVisible(R.id.ivIndicator, selectedPosition == adapterPosition)
//        if (item.canSort) when {
//            selectedPosition != adapterPosition -> setImageResource(
//                R.id.ivSort, R.mipmap.sort_default
//            )
//            item.sortAsc -> setImageResource(R.id.ivSort, R.mipmap.sort_asc)
//            else -> setImageResource(R.id.ivSort, R.mipmap.sort_desc)
//        }
    }

    /**
     * 对排序参数进行设置，并返回是否有变化
     */
    private fun sort(position: Int): Boolean {
        var hasChanged = false
        if (position != selectedPosition || data[position].canSort) {
            data[position].sortAsc =
                if (position != selectedPosition) true else !data[position].sortAsc
            selectedPosition = position
            hasChanged = true
        }
        return hasChanged
    }
}