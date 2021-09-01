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
    private val DEFAULT_TEXT_COLOR = Color.parseColor("#FF202020")
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
     * 是否显示左侧Item
     */
    var showLeft = false
        set(value) {
            if (field != value) {
                field = value
                leftText.visibility = if (field) View.VISIBLE else View.GONE
            }
        }

    /**
     * 左侧Item文字大小
     */
    var leftTextSize = DEFAULT_TEXT_SIZE
        set(value) {
            if (field != value) {
                field = value
                leftText.setTextSize(TypedValue.COMPLEX_UNIT_PX, field)
            }
        }

    /**
     * 左侧Item文字颜色
     */
    var leftTextColor = DEFAULT_TEXT_COLOR
        set(value) {
            if (field != value) {
                field = value
                leftText.setTextColor(field)
            }
        }

    /**
     * 左侧文字
     */
    var leftContent: String? = null
        set(value) {
            if (!TextUtils.equals(field, value)) {
                field = value
                setContent(leftText, field)
            }
        }

    /**
     * 左侧图标
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
     * 标题
     */
    var titleContent: String? = null
        set(value) {
            if (!TextUtils.equals(field, value)) {
                field = value
                setContent(titleText, field)
            }
        }

    /**
     * 标题文字大小
     */
    var titleTextSize = DEFAULT_TITLE_SIZE
        set(value) {
            if (field != value) {
                field = value
                titleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, field)
            }
        }

    /**
     * 标题文字颜色
     */
    var titleTextColor = Color.BLACK
        set(value) {
            if (field != value) {
                field = value
                titleText.setTextColor(field)
            }
        }

    /**
     * 是否显示右侧Item
     */
    var showRight = false
        set(value) {
            if (field != value) {
                field = value
                rightText.visibility = if (field) View.VISIBLE else View.GONE
            }
        }

    /**
     * 右侧Item文字颜色
     */
    var rightTextColor = DEFAULT_TEXT_COLOR
        set(value) {
            if (field != value) {
                field = value
                rightText.setTextColor(field)
            }
        }

    /**
     * 右侧文字大小
     */
    var rightTextSize = DEFAULT_TEXT_SIZE
        set(value) {
            if (field != value) {
                field = value
                rightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, field)
            }
        }

    /**
     * 右侧文字
     */
    var rightContent: String? = null
        set(value) {
            if (!TextUtils.equals(field, value)) {
                field = value
                setContent(rightText, field)
            }
        }

    /**
     * 右侧图标
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
     * 是否需要留出StatusBar的高度
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
            // 设置字大小
            setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize)
            // 设置字体颜色
            setTextColor(leftTextColor)
            // 设置文字
            setContent(this, leftContent)
            // 设置图标
            if (leftDrawableResId != 0) setCompoundDrawablesRelativeWithIntrinsicBounds(
                leftDrawableResId, 0, 0, 0
            )
            // 设置是否显示
            visibility = if (showLeft) View.VISIBLE else View.INVISIBLE
            // 添加到root中
            addView(this)
        }
        with(rightText) {
            // 设置字大小
            setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize)
            // 设置字体颜色
            setTextColor(rightTextColor)
            // 设置文字
            setContent(this, rightContent)
            // 设置图标
            if (rightDrawableResId != 0) setCompoundDrawablesRelativeWithIntrinsicBounds(
                rightDrawableResId, 0, 0, 0
            )
            // 设置是否显示
            visibility = if (showRight) View.VISIBLE else View.INVISIBLE
            // 添加到root中
            addView(this)
        }
        with(titleText) {
            // 设置字大小
            setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize)
            // 设置字体颜色
            setTextColor(titleTextColor)
            // 设置文字
            setContent(this, titleContent)
            // 添加到root中
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