package com.xdys.library.kit.span

import android.graphics.Color
import android.text.Selection
import android.text.Spannable
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.BackgroundColorSpan
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.widget.TextView

class ClickMovementMethod : LinkMovementMethod() {

    companion object {
        private var sInstance: ClickMovementMethod? = null

        @Synchronized
        fun getInstance(): ClickMovementMethod {
            if (sInstance == null) sInstance = ClickMovementMethod()
            return sInstance!!
        }
    }

    private val transparentSpan = BackgroundColorSpan(Color.TRANSPARENT)

    override fun onTouchEvent(widget: TextView?, buffer: Spannable?, event: MotionEvent?): Boolean {
        if (widget == null || buffer == null || event == null) return super.onTouchEvent(
            widget, buffer, event
        )
        val action = event.action
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            var x = event.x
            var y = event.y
            x -= widget.totalPaddingLeft
            y -= widget.totalPaddingTop
            x += widget.scrollX
            y += widget.scrollY
            val layout = widget.layout
            val off = layout.getOffsetForHorizontal(layout.getLineForVertical(y.toInt()), x)
            val link = buffer.getSpans(off, off, ClickableSpan::class.java)
            if (link.isNotEmpty()) {
                when (action) {
                    MotionEvent.ACTION_UP -> link[0]?.run {
                        onClick(widget)
                        buffer.setSpan(
                            transparentSpan, buffer.getSpanStart(this), buffer.getSpanEnd(this),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        Selection.removeSelection(buffer)
                    }
                    MotionEvent.ACTION_MOVE -> link[0]?.run {
                        buffer.setSpan(
                            transparentSpan, buffer.getSpanStart(this), buffer.getSpanEnd(this),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        Selection.removeSelection(buffer)
                    }
                    MotionEvent.ACTION_DOWN -> link[0]?.run {
                        buffer.setSpan(
                            transparentSpan, buffer.getSpanStart(this), buffer.getSpanEnd(this),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        Selection.removeSelection(buffer)
                    }
                }
                return true
            } else Selection.removeSelection(buffer)
        }
        return false
    }
}