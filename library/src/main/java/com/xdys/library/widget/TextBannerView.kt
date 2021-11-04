package com.xdys.library.widget

import android.content.Context
import android.text.TextUtils

import android.widget.TextView

import android.widget.LinearLayout

import android.view.Gravity

import android.graphics.drawable.Drawable

import android.view.animation.Animation

import androidx.annotation.AnimRes


import android.view.ViewGroup

import android.widget.ViewFlipper

import android.graphics.Typeface


import android.content.res.TypedArray
import android.graphics.Paint
import android.util.AttributeSet
import android.view.animation.AnimationUtils

import android.widget.RelativeLayout
import com.xdys.library.R
import com.xdys.library.widget.text.DisplayUtils
import com.xdys.library.widget.text.ITextBannerItemClickListener


class TextBannerView(context: Context, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {
    private var mViewFlipper: ViewFlipper? = null
    private var mInterval = 3000

    /**文字切换时间间隔,默认3s */
    private var isSingleLine = false

    /**文字是否为单行,默认false */
    private var mTextColor = -0x1000000

    /**设置文字颜色,默认黑色 */
    private var mTextSize = 16

    /**设置文字尺寸,默认16px */
    private var mGravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
    private var hasSetDirection = false
    private var direction = DIRECTION_BOTTOM_TO_TOP

    @AnimRes
    private var inAnimResId: Int = R.anim.anim_right_in1

    @AnimRes
    private var outAnimResId: Int = R.anim.anim_left_out1
    private var hasSetAnimDuration = false
    private var animDuration = 1500

    /**默认1.5s */
    private var mFlags = -1
    private var mTypeface = Typeface.NORMAL
    private var mDatas: List<String>? = null
    private var mListener: ITextBannerItemClickListener? = null
    private var isStarted = false
    private var isDetachedFromWindow = false

    constructor(context: Context) : this(context, null) {}

    /**初始化控件 */
    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.TextBannerViewStyle, defStyleAttr, 0)
        mInterval =
            typedArray.getInteger(R.styleable.TextBannerViewStyle_setInterval, mInterval) //文字切换时间间隔
        isSingleLine =
            typedArray.getBoolean(R.styleable.TextBannerViewStyle_setSingleLine, false) //文字是否为单行
        mTextColor =
            typedArray.getColor(R.styleable.TextBannerViewStyle_setTextColor, mTextColor) //设置文字颜色
        if (typedArray.hasValue(R.styleable.TextBannerViewStyle_setTextSize)) { //设置文字尺寸
            mTextSize = typedArray.getDimension(
                R.styleable.TextBannerViewStyle_setTextSize,
                mTextSize.toFloat()
            )
                .toInt()
            mTextSize = DisplayUtils.px2sp(context, mTextSize)
        }
        val gravityType =
            typedArray.getInt(R.styleable.TextBannerViewStyle_setGravity, GRAVITY_LEFT) //显示位置
        when (gravityType) {
            GRAVITY_LEFT -> mGravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
            GRAVITY_CENTER -> mGravity = Gravity.CENTER
            GRAVITY_RIGHT -> mGravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
        }
        hasSetAnimDuration = typedArray.hasValue(R.styleable.TextBannerViewStyle_setAnimDuration)
        animDuration =
            typedArray.getInt(R.styleable.TextBannerViewStyle_setAnimDuration, animDuration) //动画时间
        hasSetDirection = typedArray.hasValue(R.styleable.TextBannerViewStyle_setDirection)
        direction = typedArray.getInt(R.styleable.TextBannerViewStyle_setDirection, direction) //方向
        if (hasSetDirection) {
            when (direction) {
                DIRECTION_BOTTOM_TO_TOP -> {
                    inAnimResId = R.anim.anim_bottom_in
                    outAnimResId = R.anim.anim_top_out
                }
                DIRECTION_TOP_TO_BOTTOM -> {
                    inAnimResId = R.anim.anim_top_in
                    outAnimResId = R.anim.anim_bottom_out
                }
                DIRECTION_RIGHT_TO_LEFT -> {
                    inAnimResId = R.anim.anim_right_in1
                    outAnimResId = R.anim.anim_left_out1
                }
                DIRECTION_LEFT_TO_RIGHT -> {
                    inAnimResId = R.anim.anim_left_in1
                    outAnimResId = R.anim.anim_right_out1
                }
            }
        } else {
            inAnimResId = R.anim.anim_right_in1
            outAnimResId = R.anim.anim_left_out1
        }
        mFlags = typedArray.getInt(R.styleable.TextBannerViewStyle_setFlags, mFlags) //字体划线
        mFlags = when (mFlags) {
            STRIKE -> Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
            UNDER_LINE -> Paint.UNDERLINE_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
            else -> 0 or Paint.ANTI_ALIAS_FLAG
        }
        mTypeface = typedArray.getInt(R.styleable.TextBannerViewStyle_setTypeface, mTypeface) //字体样式
        when (mTypeface) {
            TYPE_BOLD -> mTypeface = Typeface.BOLD
            TYPE_ITALIC -> mTypeface = Typeface.ITALIC
            TYPE_ITALIC_BOLD -> mTypeface = Typeface.ITALIC or Typeface.BOLD
            else -> {
            }
        }
        mViewFlipper = ViewFlipper(getContext()) //new 一个ViewAnimator
        mViewFlipper!!.layoutParams =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addView(mViewFlipper)
        startViewAnimator()
        //设置点击事件
        mViewFlipper!!.setOnClickListener {
            val position = mViewFlipper!!.displayedChild //当前显示的子视图的索引位置
            if (mListener != null) {
                mListener?.onItemClick(mDatas!![position], position)
            }
        }
    }

    /**暂停动画 */
    fun stopViewAnimator() {
        if (isStarted) {
            removeCallbacks(mRunnable)
            isStarted = false
        }
    }

    /**开始动画 */
    fun startViewAnimator() {
        if (!isStarted) {
            if (!isDetachedFromWindow) {
                isStarted = true
                postDelayed(mRunnable, mInterval.toLong())
            }
        }
    }

    /**
     * 设置延时间隔
     */
    private val mRunnable: AnimRunnable = AnimRunnable()

    private inner class AnimRunnable : Runnable {
        override fun run() {
            if (isStarted) {
                setInAndOutAnimation(inAnimResId, outAnimResId)
                mViewFlipper!!.showNext() //手动显示下一个子view。
                postDelayed(this, (mInterval + animDuration).toLong())
            } else {
                stopViewAnimator()
            }
        }
    }

    /**
     * 设置进入动画和离开动画
     *
     * @param inAnimResId  进入动画的resID
     * @param outAnimResID 离开动画的resID
     */
    private fun setInAndOutAnimation(@AnimRes inAnimResId: Int, @AnimRes outAnimResID: Int) {
        val inAnim: Animation = AnimationUtils.loadAnimation(context, inAnimResId)
        inAnim.duration = animDuration.toLong()
        mViewFlipper!!.inAnimation = inAnim
        val outAnim: Animation = AnimationUtils.loadAnimation(context, outAnimResID)
        outAnim.duration = animDuration.toLong()
        mViewFlipper!!.outAnimation = outAnim
    }

    /**设置数据集合 */
    fun setDatas(datas: List<String>?) {
        mDatas = datas
        if (DisplayUtils.notEmpty(mDatas)) {
            mViewFlipper!!.removeAllViews()
            for (i in mDatas!!.indices) {
                val textView = TextView(context)
                setTextView(textView, i)
                mViewFlipper!!.addView(textView, i) //添加子view,并标识子view位置
            }
        }
    }

    /**
     * 设置数据集合伴随drawable-icon
     * @param datas 数据
     * @param drawable 图标
     * @param size 图标尺寸
     * @param direction 图标位于文字方位
     */
    fun setDatasWithDrawableIcon(
        datas: List<String>?,
        drawable: Drawable,
        size: Int,
        direction: Int
    ) {
        mDatas = datas
        if (DisplayUtils.isEmpty(mDatas)) {
            return
        }
        mViewFlipper!!.removeAllViews()
        for (i in mDatas!!.indices) {
            val textView = TextView(context)
            setTextView(textView, i)
            textView.compoundDrawablePadding = 8
            val scale = resources.displayMetrics.density // 屏幕密度 ;
            val muchDp = (size * scale + 0.5f).toInt()
            drawable.setBounds(0, 0, muchDp, muchDp)
            if (direction == Gravity.LEFT) {
                textView.setCompoundDrawables(drawable, null, null, null) //左边
            } else if (direction == Gravity.TOP) {
                textView.setCompoundDrawables(null, drawable, null, null) //顶部
            } else if (direction == Gravity.RIGHT) {
                textView.setCompoundDrawables(null, null, drawable, null) //右边
            } else if (direction == Gravity.BOTTOM) {
                textView.setCompoundDrawables(null, null, null, drawable) //底部
            }
            val linearLayout = LinearLayout(context)
            linearLayout.orientation = LinearLayout.HORIZONTAL //水平方向
            linearLayout.gravity = mGravity //子view显示位置跟随TextView
            val param = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            linearLayout.addView(textView, param)
            mViewFlipper!!.addView(linearLayout, i) //添加子view,并标识子view位置
        }
    }

    /**设置TextView */
    private fun setTextView(textView: TextView, position: Int) {
        textView.text = mDatas!![position]
        //任意设置你的文字样式，在这里
        textView.isSingleLine = isSingleLine
        textView.ellipsize = TextUtils.TruncateAt.END
        textView.setTextColor(mTextColor)
        textView.textSize = mTextSize.toFloat()
        textView.gravity = mGravity
        textView.paint.flags = mFlags //字体划线
        textView.setTypeface(null, mTypeface) //字体样式
    }

    /**设置点击监听事件回调 */
    fun setItemOnClickListener(listener: ITextBannerItemClickListener?) {
        mListener = listener
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        isDetachedFromWindow = true
        stopViewAnimator()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        isDetachedFromWindow = false
        startViewAnimator()
    }

    companion object {
        /**文字显示位置,默认左边居中 */
        private const val GRAVITY_LEFT = 0
        private const val GRAVITY_CENTER = 1
        private const val GRAVITY_RIGHT = 2
        private const val DIRECTION_BOTTOM_TO_TOP = 0
        private const val DIRECTION_TOP_TO_BOTTOM = 1
        private const val DIRECTION_RIGHT_TO_LEFT = 2
        private const val DIRECTION_LEFT_TO_RIGHT = 3

        /**文字划线 */
        private const val STRIKE = 0
        private const val UNDER_LINE = 1

        /**设置字体类型：加粗、斜体、斜体加粗 */
        private const val TYPE_NORMAL = 0
        private const val TYPE_BOLD = 1
        private const val TYPE_ITALIC = 2
        private const val TYPE_ITALIC_BOLD = 3
    }

    init {
        init(context, attrs, 0)
    }
}