package com.xdys.library.widget.code

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.res.use
import androidx.core.view.children
import com.xdys.library.R
import com.xdys.library.extension.px

/**
 * TODO 数字密码格式未支持
 */
class VerificationCodeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs), View.OnKeyListener, View.OnFocusChangeListener, TextWatcher,
    View.OnTouchListener {


    private val DEFAULT_TEXT_SIZE = 30F.px
    private val DEFAULT_SPACING = 12.px
    private val DEFAULT_SIZE = 60.px
    private val lengthFilters = arrayOf(InputFilter.LengthFilter(1))

    enum class Type {
        /**
         * 数字
         */
        NUMBER,

        /**
         * 文字
         */
        TEXT,

        /**
         * 数字密码
         */
        NUMBER_PASSWORD,

        /**
         * 文字密码
         */
        TEXT_PASSWORD
    }

    enum class Mode {
        /**
         * 输入框固定大小
         */
        FIX,

        /**
         * 输入框等大均分
         */
        AVERAGE
    }

    interface OnCodeCompleteListener {
        fun complete(content: String)
    }

    /**
     * 输入框数量
     */
    var codeCount: Int = 4
        set(value) {
            if (field != value) {
                field = value
                if (isLaidOut) invalidate()
            }
        }

    /**
     * 输入框类型
     */
    var codeType: Type = Type.NUMBER
        set(value) {
            if (field != value) {
                field = value
                if (isLaidOut) for (child in children) setInputType(child as EditText)
            }
        }

    /**
     * 排列模式
     */
    var codeMode: Mode = Mode.AVERAGE
        set(value) {
            if (field != value) {
                field = value
                if (isLaidOut) invalidate()
            }
        }

    /**
     * 文字颜色
     */
    var codeTextColor = Color.BLACK
        set(value) {
            if (field != value) {
                field = value
                if (isLaidOut) for (child in children) (child as EditText).setTextColor(field)
            }
        }

    /**
     * 文字大小
     */
    var codeTextSize = DEFAULT_TEXT_SIZE
        set(value) {
            if (field != value) {
                field = value
                if (isLaidOut) for (child in children) (child as EditText).setTextSize(
                    TypedValue.COMPLEX_UNIT_PX, field
                )
            }
        }

    /**
     * 输入框之间的间隔
     */
    var spacing = DEFAULT_SPACING
        set(value) {
            if (field != value) {
                field = value
                if (isLaidOut) invalidate()
            }
        }

    /**
     * 输入框的宽度(只有mode为FIX才有效果)
     */
    var codeWidth = DEFAULT_SIZE
        set(value) {
            if (field != value) {
                field = value
                if (isLaidOut && codeMode == Mode.FIX) invalidate()
            }
        }

    /**
     * 输入框的高度(只有mode为FIX才有效果)
     */
    var codeHeight = DEFAULT_SIZE
        set(value) {
            if (field != value) {
                field = value
                if (isLaidOut && codeMode == Mode.FIX) invalidate()
            }
        }

    /**
     * 输入框的高宽比(只有mode为AVERAGE才有效果)
     */
    var codeRatio = 1.0F
        set(value) {
            if (field != value) {
                field = value
                if (isLaidOut && codeMode == Mode.AVERAGE) invalidate()
            }
        }

    /**
     * 输入框空背景
     */
    @DrawableRes
    var emptyBackground: Int = 0
        set(value) {
            if (field != value) {
                field = value
                if (isLaidOut) invalidate()
            }
        }

    /**
     * 输入框不为空背景
     */
    @DrawableRes
    var fillBackground: Int = 0
        set(value) {
            if (field != value) {
                field = value
                if (isLaidOut) invalidate()
            }
        }

    /**
     * 输入框内容
     */
    val content: String
        get() = buildString {
            for (child in children) (child as EditText).let {
                if (!it.text.isNullOrBlank()) append(it.text)
            }
        }

    /**
     * 验证码输入完成监听
     */
    var onCodeCompleteListener: OnCodeCompleteListener? = null

    init {
        context.obtainStyledAttributes(attrs, R.styleable.VerificationCodeView).use {
            codeCount = it.getInt(R.styleable.VerificationCodeView_codeCount, 4)
            codeType = Type.values()[it.getInt(
                R.styleable.VerificationCodeView_codeType, Type.NUMBER.ordinal
            )]
            codeMode = Mode.values()[it.getInt(
                R.styleable.VerificationCodeView_codeMode, Mode.FIX.ordinal
            )]
            codeTextColor = it.getColor(R.styleable.VerificationCodeView_codeTextColor, Color.BLACK)
            codeTextSize = it.getDimension(
                R.styleable.VerificationCodeView_codeTextSize, DEFAULT_TEXT_SIZE
            )
            spacing = it.getDimensionPixelSize(
                R.styleable.VerificationCodeView_codeSpacing, DEFAULT_SPACING
            )
            if (it.hasValue(R.styleable.VerificationCodeView_codeEmptyBackground))
                emptyBackground = it.getResourceId(
                    R.styleable.VerificationCodeView_codeEmptyBackground, 0
                )
            if (it.hasValue(R.styleable.VerificationCodeView_codeFillBackground))
                fillBackground = it.getResourceId(
                    R.styleable.VerificationCodeView_codeFillBackground, 0
                )
            if (it.hasValue(R.styleable.VerificationCodeView_codeWidth))
                codeWidth = it.getDimensionPixelSize(
                    R.styleable.VerificationCodeView_codeWidth, DEFAULT_SIZE
                )
            if (it.hasValue(R.styleable.VerificationCodeView_codeHeight))
                codeHeight = it.getDimensionPixelSize(
                    R.styleable.VerificationCodeView_codeHeight, DEFAULT_SIZE
                )
            if (it.hasValue(R.styleable.VerificationCodeView_codeRatio))
                codeRatio = it.getFloat(R.styleable.VerificationCodeView_codeRatio, 1.0F)
        }

        orientation = HORIZONTAL
        // 添加EditText
        for (index in 0 until codeCount) addView(initEditText(index))
        setOnTouchListener(this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        for (index in 0 until childCount) getChildAt(index).let {
            it.layoutParams = createOrUpdateLayoutParams(it.layoutParams as LayoutParams, index)
        }
    }

    /**
     * 监听删除按钮,删除最后一个字符
     */
    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL && event?.action == KeyEvent.ACTION_DOWN) {
            for (index in childCount - 1 downTo 0) (getChildAt(index) as EditText).let {
                if (!it.text.isNullOrBlank()) {
                    it.text = null
                    it.setBackgroundResource(emptyBackground)
                    it.requestFocus()
                    return true
                }
            }
            return true
        } else return false
    }

    /**
     * 输入框焦点监听
     */
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus) changeFocus()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    /**
     * 输入完成监听
     */
    override fun afterTextChanged(s: Editable?) {
        if (s == null) return
        if (s.isBlank()) s.delete(0, s.length) else {
            changeFocus()
            if (!(getChildAt(childCount - 1) as EditText).text.isNullOrBlank()) {
                onCodeCompleteListener?.complete(content)
            }
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return if (event?.action == MotionEvent.ACTION_DOWN && content.length < codeCount) {
            changeFocus()
            true
        } else false
    }

    /**
     * 清除内容
     * @param focus 是否拥有焦点
     */
    fun clear(focus: Boolean) {
        for (index in 0 until childCount) (getChildAt(index) as EditText).let {
            it.text = null
            if (emptyBackground != 0) it.setBackgroundResource(emptyBackground)
            else it.background = null
            if (index == 0 && focus) it.requestFocus() else it.clearFocus()
        }
    }

    /**
     * 配置每个输入框
     */
    private fun initEditText(index: Int): EditText = EditText(context).apply {
        layoutParams = createOrUpdateLayoutParams(index = index)
        gravity = Gravity.CENTER
        inputType = InputType.TYPE_NUMBER_VARIATION_PASSWORD
        textAlignment = TEXT_ALIGNMENT_CENTER
        isSingleLine = true
        setPadding(0, 0, 0, 0)
        paint.isFakeBoldText = true
        setTextSize(TypedValue.COMPLEX_UNIT_PX, codeTextSize)
        setTextColor(codeTextColor)
        filters = lengthFilters
        if (emptyBackground != 0) setBackgroundResource(emptyBackground)
        else background = null
        setOnKeyListener(this@VerificationCodeView)
        onFocusChangeListener = this@VerificationCodeView
        addTextChangedListener(this@VerificationCodeView)
    }

    /**
     * 生成或者更新输入框的宽高
     */
    private fun createOrUpdateLayoutParams(lp: LayoutParams? = null, index: Int): LayoutParams {
        return (lp ?: LayoutParams(-1, -1)).apply {
            leftMargin = if (index == 0) 0 else spacing
            if (codeMode == Mode.FIX) {
                width = codeWidth
                height = codeHeight
            } else if (codeMode == Mode.AVERAGE) {
                width = (measuredWidth - spacing * (codeCount - 1)) / codeCount
                height = (width * codeRatio).toInt()
            }
        }
    }

    /**
     * 将焦点移交给第一个空输入框
     */
    private fun changeFocus() {
        var hasFocus = false
        for (child in children) (child as EditText).let {
            if (it.text.isNullOrBlank()) {
                if (emptyBackground != 0) it.setBackgroundResource(emptyBackground)
                else it.background = null
                if (!hasFocus) {
                    it.requestFocus()
                    hasFocus = true
                }
            } else {
                if (fillBackground != 0) it.setBackgroundResource(fillBackground)
                else it.background = null
            }
        }
    }

    /**
     * 设置输入类型
     */
    private fun setInputType(editText: EditText) = with(editText) {
        when (codeType) {
            Type.NUMBER -> inputType = InputType.TYPE_CLASS_NUMBER
            Type.NUMBER_PASSWORD -> {
                inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                transformationMethod = NumberPasswordTransformationMethod()
            }
            Type.TEXT -> inputType = InputType.TYPE_CLASS_TEXT
            Type.TEXT_PASSWORD -> inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }
}