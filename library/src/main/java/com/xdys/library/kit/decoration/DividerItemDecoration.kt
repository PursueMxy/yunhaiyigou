package com.xdys.library.kit.decoration

import android.graphics.*
import android.util.SparseArray
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.util.containsKey
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.flexbox.FlexboxLayoutManager

/**
 * 给常规的RecyclerView添加间距(默认同一行的每个item宽度是一样的)
 * @param vertical 每一行item之间的间距
 * @param horizontal 每一列item置间的间距
 * @param dividerColor 间距的颜色,默认透明，不填充颜色
 */
class DividerItemDecoration(
    private val vertical: Int = 0,
    private val horizontal: Int = 0,
    @ColorInt private val dividerColor: Int = Color.TRANSPARENT
) : RecyclerView.ItemDecoration() {

    private val list: SparseArray<RectF> = SparseArray()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = dividerColor
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        var left = 0
        var right = 0
        var top = 0
        var bottom = 0
        var position = 0
        when (val layoutManager = parent.layoutManager) {
            is GridLayoutManager -> with(layoutManager) {
                position = getPosition(view)
                // RecyclerView的总宽度
                val totalWidth = parent.measuredWidth - parent.paddingStart - parent.paddingEnd
                // 每个item的实际宽度
                val realItemWidth = (totalWidth - horizontal * (spanCount - 1)) / spanCount
                // 每个item的实际间距
                val itemTotalDivider = totalWidth / spanCount - realItemWidth
                // item在第几列
                val itemSpanIndex = spanSizeLookup.getSpanIndex(position, spanCount)
                // item在第几行
                val itemRowIndex = spanSizeLookup.getSpanGroupIndex(position, spanCount)
                left = when {
                    spanCount == spanSizeLookup.getSpanSize(position) || itemSpanIndex == 0 -> 0
                    itemSpanIndex == spanCount - 1 -> itemTotalDivider
                    else -> itemTotalDivider / 2
                }
                right = when {
                    spanCount == spanSizeLookup.getSpanSize(position) || itemSpanIndex == spanCount - 1 -> 0
                    itemSpanIndex == 0 -> itemTotalDivider
                    else -> itemTotalDivider / 2
                }
                top = 0
                bottom = if (
                    position + spanCount / spanSizeLookup.getSpanSize(position) - itemSpanIndex
                    > parent.adapter?.itemCount ?: 0
                ) 0 else vertical
            }
            is LinearLayoutManager -> with(layoutManager) {
                position = getPosition(view)
                val isVertical = orientation == LinearLayoutManager.VERTICAL
                left = if (position == 0 || isVertical) horizontal else 0
                right = if (position == (parent.adapter?.itemCount ?: 0) - 1 || isVertical)
                    horizontal else 0
                top = 0
                bottom = if (position == (parent.adapter?.itemCount ?: 0) - 1 || !isVertical) 0 else
                    vertical
            }
            is StaggeredGridLayoutManager -> Unit
            is FlexboxLayoutManager -> Unit
        }
        outRect.set(left, top, right, bottom)
        // 将数据保存到列表中
        if (!list.containsKey(position)) list.put(
            position, RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
        )
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (dividerColor == Color.TRANSPARENT) return
        for (index in 0 until parent.childCount) {
            val view = parent.getChildAt(index)
            val position = parent.getChildAdapterPosition(view)
            if (list.containsKey(position)) list[position].let {
                if (it.left > 0) c.drawRect(
                    view.left.toFloat(), view.top.toFloat(),
                    view.left + it.left, view.bottom.toFloat(), paint
                )
                if (it.right > 0) c.drawRect(
                    view.right - it.right, view.top.toFloat(),
                    view.right.toFloat(), view.bottom.toFloat(), paint
                )
                if (it.top > 0) c.drawRect(
                    view.left + it.left, view.top.toFloat(),
                    view.right - it.right, view.top + it.top, paint
                )
                if (it.bottom > 0) c.drawRect(
                    view.left + it.left, view.bottom - it.bottom,
                    view.right - it.right, view.bottom.toFloat(), paint
                )
            }
        }
    }
}