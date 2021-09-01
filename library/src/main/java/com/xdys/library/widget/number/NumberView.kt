package com.xdys.library.widget.number

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.use
import androidx.databinding.BindingAdapter
import com.xdys.library.R
import com.xdys.library.extension.px

class NumberView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs), INumberChange {

    private val DEFAULT_SIZE = 20.px

    var subBackground: Int = 0
    var subSrc: Int = 0
    var addBackground: Int = 0
    var contentBackground: Int = 0
    var addSrc: Int = 0
    var contentSize: Float = 12F.px
    var contentColor: Int = Color.BLACK
    var contentWidthPercent: Float = 1.5F
    var itemSize: Int = 0

    var callback: OnNumberChangeListener? = null

    override var maxNumber: Int = 99
    override var currentNumber: Int = 0
        get() = viewNumber.text?.toString()?.toInt() ?: 0
        set(value) {
            if (field != value) {
                field = value
                viewNumber.text = value.toString()
                afterNumberChange(field)
            }
        }
    override val btnSub: ImageView = AppCompatImageView(context).apply {
        layoutParams = LayoutParams(DEFAULT_SIZE, DEFAULT_SIZE)
        scaleType = ImageView.ScaleType.CENTER_INSIDE
        setOnClickListener {
            val temp = currentNumber - 1
            if (shouldInterceptSub(temp)) return@setOnClickListener else currentNumber = temp
        }
    }
    override val btnAdd: ImageView = AppCompatImageView(context).apply {
        layoutParams = LayoutParams(DEFAULT_SIZE, DEFAULT_SIZE)
        scaleType = ImageView.ScaleType.CENTER_INSIDE
        setOnClickListener {
            val temp = currentNumber + 1
            if (shouldInterceptAdd(temp)) return@setOnClickListener else currentNumber = temp
        }
    }

    override val viewNumber: TextView = TextView(context).apply {
        layoutParams = LayoutParams(DEFAULT_SIZE, DEFAULT_SIZE)
        gravity = Gravity.CENTER
        text = "0"
    }

    init {
        orientation = HORIZONTAL

        context.obtainStyledAttributes(attrs, R.styleable.NumberView).use {
            if (it.hasValue(R.styleable.NumberView_addBackground)) {
                addBackground = it.getResourceId(R.styleable.NumberView_addBackground, 0)
            }
            if (it.hasValue(R.styleable.NumberView_addSrc)) {
                addSrc = it.getResourceId(R.styleable.NumberView_addSrc, 0)
            }
            if (it.hasValue(R.styleable.NumberView_subBackground)) {
                subBackground = it.getResourceId(R.styleable.NumberView_subBackground, 0)
            }
            if (it.hasValue(R.styleable.NumberView_subSrc)) {
                subSrc = it.getResourceId(R.styleable.NumberView_subSrc, 0)
            }
            if (it.hasValue(R.styleable.NumberView_contentBackground)) {
                contentBackground = it.getResourceId(R.styleable.NumberView_contentBackground, 0)
            }
            contentSize = it.getDimension(R.styleable.NumberView_contentSize, contentSize)
            contentColor = it.getColor(R.styleable.NumberView_contentColor, contentColor)
            contentWidthPercent = it.getFloat(
                R.styleable.NumberView_contentWidthPercent, contentWidthPercent
            )
            itemSize = it.getDimensionPixelSize(R.styleable.NumberView_itemSize, itemSize)
        }

        with(btnSub) {
            if (subBackground != 0) setBackgroundResource(subBackground)
            if (subSrc != 0) setImageResource(subSrc)
            addView(this)
        }
        with(viewNumber) {
            if (contentBackground != 0) setBackgroundResource(contentBackground)
            setTextSize(TypedValue.COMPLEX_UNIT_PX, contentSize)
            setTextColor(contentColor)
            addView(this)
        }
        with(btnAdd) {
            if (addBackground != 0) setBackgroundResource(addBackground)
            if (addSrc != 0) setImageResource(addSrc)
            addView(this)
        }
    }

    override fun shouldInterceptAdd(afterChange: Int): Boolean {
        val over = afterChange > maxNumber
        return callback?.shouldIntercept(afterChange, 1) ?: true && !over
    }

    override fun shouldInterceptSub(afterChange: Int): Boolean {
        val over = afterChange < 0
        return callback?.shouldIntercept(afterChange, 2) ?: true && !over
    }

    override fun afterNumberChange(current: Int) {
        btnAdd.isEnabled = current + 1 <= maxNumber
        btnSub.isEnabled = current - 1 >= 0
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val realItemSize: Int
        if (widthMode == MeasureSpec.AT_MOST) {
            realItemSize = if (itemSize > 0) itemSize else DEFAULT_SIZE
            val dividerWidth = if (dividerDrawable == null) 0 else {
                var count = 0
                when {
                    (showDividers and SHOW_DIVIDER_END) != 0 -> count++
                    (showDividers and SHOW_DIVIDER_BEGINNING) != 0 -> count++
                    (showDividers and SHOW_DIVIDER_MIDDLE) != 0 -> count += childCount - 1
                    else -> count = 0
                }
                (dividerDrawable?.intrinsicWidth ?: 0) * count
            }
            widthSize =
                (realItemSize * (2 + contentWidthPercent) + paddingStart + paddingEnd + dividerWidth).toInt()
        } else {
            realItemSize = (widthSize / (2 + contentWidthPercent)).toInt()
        }
        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
            heightSize = realItemSize
        }

        btnSub.layoutParams.width = realItemSize
        btnSub.layoutParams.height = heightSize
        btnAdd.layoutParams.width = realItemSize
        btnAdd.layoutParams.height = heightSize
        viewNumber.layoutParams.width = (realItemSize * contentWidthPercent).toInt()
        viewNumber.layoutParams.height = heightSize

        super.onMeasure(
            MeasureSpec.makeMeasureSpec(widthSize, widthMode),
            MeasureSpec.makeMeasureSpec(heightSize, heightMode)
        )
    }
}

@BindingAdapter("num")
fun current(numberView: NumberView, num: Int) {
    numberView.currentNumber = num
}

@BindingAdapter("max_num")
fun maxNumber(numberView: NumberView, max: Int) {
    numberView.maxNumber = max
}