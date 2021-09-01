package com.xdys.library.widget

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.use
import androidx.core.widget.doAfterTextChanged
import com.xdys.library.R

class DeleteEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs, R.attr.editTextStyle) {

    private var deleteIcon: Drawable? = null
    private var showDeleteIcon: Boolean = false

    init {
        context.obtainStyledAttributes(attrs, R.styleable.DeleteEditText).use {
            if (it.hasValue(R.styleable.DeleteEditText_icon)) deleteIcon = it.getDrawable(
                R.styleable.DeleteEditText_icon
            )
        }
        doAfterTextChanged { showOrHideIcon() }
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        showOrHideIcon()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN &&
            event.x > width - paddingEnd - (deleteIcon?.intrinsicWidth ?: 0)
        ) {
            text = null
            return true
        }
        return super.onTouchEvent(event)
    }

    private fun showOrHideIcon() {
        if (deleteIcon == null) return
        showDeleteIcon = if (hasFocus() && !text.isNullOrBlank()) {
            if (compoundDrawablesRelative[2] == null) setCompoundDrawablesRelativeWithIntrinsicBounds(
                compoundDrawablesRelative[0], compoundDrawablesRelative[1], deleteIcon,
                compoundDrawablesRelative[3]
            )
            true
        } else {
            setCompoundDrawablesRelativeWithIntrinsicBounds(
                compoundDrawablesRelative[0], compoundDrawablesRelative[1], null,
                compoundDrawablesRelative[3]
            )
            false
        }
    }
}