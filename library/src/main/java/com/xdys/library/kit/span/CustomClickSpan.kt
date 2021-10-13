package com.xdys.library.kit.span

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.xdys.library.R
import com.xdys.library.extension.context

class CustomClickSpan(
    private val onClick: (v: View) -> Unit,
    @ColorRes private val color: Int = R.color.RE3
) : ClickableSpan() {
    override fun onClick(widget: View) {
        onClick.invoke(widget)
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.color = ContextCompat.getColor(context, color)
        ds.isUnderlineText = false
    }
}