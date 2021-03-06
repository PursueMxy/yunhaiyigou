package com.xdys.library.widget

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.res.use
import com.xdys.library.R
import com.xdys.library.extension.dp
import com.xdys.library.extension.px
import com.xdys.library.extension.sp


class TitleBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val DEFAULT_HEIGHT = 48.px
    private val DEFAULT_TEXT_SIZE = 15F.sp
    private val DEFAULT_TITLE_SIZE = 18F.sp
    private val DEFAULT_PADDING = 8.dp
    private val DEFAULT_BAR_PADDING = 10.dp
    private val DEFAULT_TEXT_COLOR = Color.parseColor("#333333")
    private val statusBarHeight = resources.run {
        getDimensionPixelSize(
            getIdentifier("status_bar_height", "dimen", "android")
        ).let {
            if (it > 0) it else 25.px
        }
    }

    private val leftText: TextView = TextView(context).apply {
        setOnClickListener {
            if (context is Activity) context.onBackPressed()
        }
        gravity = Gravity.CENTER_VERTICAL
        setPadding(5.px, 5.px, 5.px, 5.px)
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, DEFAULT_HEIGHT).apply {
            gravity = Gravity.START
        }
    }

    private val rightText: TextView = TextView(context).apply {
        gravity = Gravity.CENTER_VERTICAL
        setPadding(5.px, 5.px, 5.px, 5.px)
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, DEFAULT_HEIGHT).apply {
            gravity = Gravity.END
        }
    }

    private val titleText: TextView = TextView(context).apply {
        gravity = Gravity.CENTER_VERTICAL
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, DEFAULT_HEIGHT).apply {
            gravity = Gravity.CENTER_HORIZONTAL
        }
    }

    /**
     * ??????????????????Item
     */
    var showLeft = false
        set(value) {
            if (field != value) {
                field = value
                leftText.visibility = if (field) View.VISIBLE else View.GONE
            }
        }

    /**
     * ??????Item????????????
     */
    var leftTextSize = DEFAULT_TEXT_SIZE
        set(value) {
            if (field != value) {
                field = value
                leftText.setTextSize(TypedValue.COMPLEX_UNIT_PX, field)
            }
        }

    /**
     * ??????Item????????????
     */
    var leftTextColor = DEFAULT_TEXT_COLOR
        set(value) {
            if (field != value) {
                field = value
                leftText.setTextColor(field)
            }
        }

    /**
     * ????????????
     */
    var leftContent: String? = null
        set(value) {
            if (!TextUtils.equals(field, value)) {
                field = value
                setContent(leftText, field)
            }
        }

    /**
     * ????????????
     */
    var leftDrawableResId = 0
        set(value) {
            if (field != value) {
                field = value
                if (field != 0) leftText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    field, 0, 0, 0
                )
            }
        }

    /**
     * ??????
     */
    var titleContent: String? = null
        set(value) {
            if (!TextUtils.equals(field, value)) {
                field = value
                setContent(titleText, field)
            }
        }

    /**
     * ??????????????????
     */
    var titleTextSize = DEFAULT_TITLE_SIZE
        set(value) {
            if (field != value) {
                field = value
                titleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, field)
            }
        }

    /**
     * ??????????????????
     */
    var titleTextColor = Color.BLACK
        set(value) {
            if (field != value) {
                field = value
                titleText.setTextColor(field)
            }
        }

    /**
     * ??????????????????Item
     */
    var showRight = false
        set(value) {
            if (field != value) {
                field = value
                rightText.visibility = if (field) View.VISIBLE else View.GONE
            }
        }

    /**
     * ??????Item????????????
     */
    var rightTextColor = DEFAULT_TEXT_COLOR
        set(value) {
            if (field != value) {
                field = value
                rightText.setTextColor(field)
            }
        }

    /**
     * ??????????????????
     */
    var rightTextSize = DEFAULT_TEXT_SIZE
        set(value) {
            if (field != value) {
                field = value
                rightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, field)
            }
        }

    /**
     * ????????????
     */
    var rightContent: String? = null
        set(value) {
            if (!TextUtils.equals(field, value)) {
                field = value
                setContent(rightText, field)
            }
        }

    /**
     * ????????????
     */
    var rightDrawableResId = 0
        set(value) {
            if (field != value) {
                field = value
                if (field != 0) rightText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    field, 0, 0, 0
                )
            }
        }

    /**
     * ??????????????????StatusBar?????????
     */
    var paddingStatusBar = true
        set(value) {
            if (field != value) {
                field = value
                if (field) setPadding(DEFAULT_BAR_PADDING, statusBarHeight, DEFAULT_BAR_PADDING, 0)
                else setPadding(DEFAULT_BAR_PADDING, 0, DEFAULT_BAR_PADDING, 0)
            }
        }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.TitleBar).use {
            showLeft = it.getBoolean(R.styleable.TitleBar_show_left, false)
            leftContent = it.getString(R.styleable.TitleBar_left_content)
            leftTextSize = it.getDimension(R.styleable.TitleBar_left_text_size, DEFAULT_TEXT_SIZE)
            leftTextColor = it.getColor(R.styleable.TitleBar_left_text_color, DEFAULT_TEXT_COLOR)
            leftDrawableResId =
                it.getResourceId(R.styleable.TitleBar_left_drawable, 0)
            titleContent = it.getString(R.styleable.TitleBar_title_content)
            titleTextSize =
                it.getDimension(R.styleable.TitleBar_title_text_size, DEFAULT_TITLE_SIZE)
            titleTextColor = it.getColor(R.styleable.TitleBar_title_text_color, Color.BLACK)
            showRight = it.getBoolean(R.styleable.TitleBar_show_right, false)
            rightContent = it.getString(R.styleable.TitleBar_right_content)
            rightTextSize = it.getDimension(R.styleable.TitleBar_right_text_size, DEFAULT_TEXT_SIZE)
            rightTextColor = it.getColor(R.styleable.TitleBar_right_text_color, DEFAULT_TEXT_COLOR)
            rightDrawableResId = it.getResourceId(R.styleable.TitleBar_right_drawable, 0)
            paddingStatusBar = it.getBoolean(R.styleable.TitleBar_padding_status_bar, true)
        }

        with(leftText) {
            // ???????????????
            setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize)
            // ??????????????????
            setTextColor(leftTextColor)
            // ????????????
            setContent(this, leftContent)
            // ????????????
            if (leftDrawableResId != 0) setCompoundDrawablesRelativeWithIntrinsicBounds(
                leftDrawableResId, 0, 0, 0
            )
            // ??????????????????
            visibility = if (showLeft) View.VISIBLE else View.INVISIBLE
            // ?????????root???
            addView(this)
        }
        with(rightText) {
            // ???????????????
            setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize)
            // ??????????????????
            setTextColor(rightTextColor)
            // ????????????
            setContent(this, rightContent)
            // ????????????
            if (rightDrawableResId != 0) setCompoundDrawablesRelativeWithIntrinsicBounds(
                rightDrawableResId, 0, 0, 0
            )
            // ??????????????????
            visibility = if (showRight) View.VISIBLE else View.INVISIBLE
            // ?????????root???
            addView(this)
        }
        with(titleText) {
            // ???????????????
            setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize)
            // ??????????????????
            setTextColor(titleTextColor)
            // ????????????
            setContent(this, titleContent)
            // ?????????root???
            addView(this)
        }

        if (paddingStatusBar) {
            setPadding(DEFAULT_BAR_PADDING, statusBarHeight, DEFAULT_BAR_PADDING, 0)
        } else setPadding(DEFAULT_BAR_PADDING, 0, DEFAULT_BAR_PADDING, 0)
    }

    private fun setContent(textView: TextView, content: String?) {
        textView.text = content
        textView.compoundDrawablePadding = if (!content.isNullOrBlank()) DEFAULT_PADDING else 0
    }

    fun setOnLeftClickListener(listener: OnClickListener) {
        leftText.setOnClickListener(listener)
    }

    fun setOnRightClickListener(listener: OnClickListener) {
        rightText.setOnClickListener(listener)
    }
}