package com.xdys.library.widget

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.use
import androidx.core.widget.NestedScrollView
import com.xdys.library.R
import com.xdys.library.extension.screenHeight

class MaxHeightScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : NestedScrollView(context, attrs, defStyle) {

    var maxHeight: Int = screenHeight / 2

    init {
        context.obtainStyledAttributes(attrs, R.styleable.MaxHeightScrollView).use {
            if (it.hasValue(R.styleable.MaxHeightScrollView_maxHeight)) maxHeight =
                it.getDimensionPixelSize(R.styleable.MaxHeightScrollView_maxHeight, maxHeight)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
            widthMeasureSpec,
            MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST)
        )
    }
}