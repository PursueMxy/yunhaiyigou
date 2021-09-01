package com.xdys.library.widget.number

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.animation.*
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.use
import com.xdys.library.R
import com.xdys.library.extension.px

class AnimationNumberView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs), INumberChange {

    private val DEFAULT_SIZE = 24.px

    var callback: OnNumberChangeListener? = null

    /************************    动画    ************************/
    private val subShowAnim by lazy {
        AnimationSet(true).apply {
            duration = 300
            addAnimation(RotateAnimation(0F, -180F, RELATIVE_TO_SELF, 0.5F, RELATIVE_TO_SELF, 0.5F))
            addAnimation(TranslateAnimation(DEFAULT_SIZE * 2F, 0F, 0F, 0F))
        }
    }
    private val subHideAnim by lazy {
        AnimationSet(true).apply {
            duration = 300
            addAnimation(RotateAnimation(0F, 180F, RELATIVE_TO_SELF, 0.5F, RELATIVE_TO_SELF, 0.5F))
            addAnimation(TranslateAnimation(0F, DEFAULT_SIZE * 2F, 0F, 0F))
        }
    }
    private val addShowAnim by lazy {
        RotateAnimation(0F, -90F, RELATIVE_TO_SELF, 0.5F, RELATIVE_TO_SELF, 0.5F).apply {
            duration = 300
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    btnSub.visibility = VISIBLE
                }

                override fun onAnimationEnd(animation: Animation?) {
                    viewNumber.visibility = VISIBLE
                }

                override fun onAnimationRepeat(animation: Animation?) = Unit
            })
        }
    }
    private val addHideAnim by lazy {
        RotateAnimation(0F, 90F, RELATIVE_TO_SELF, 0.5F, RELATIVE_TO_SELF, 0.5F).apply {
            duration = 300
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    viewNumber.visibility = INVISIBLE
                }

                override fun onAnimationEnd(animation: Animation?) {
                    btnSub.visibility = INVISIBLE
                }

                override fun onAnimationRepeat(animation: Animation?) = Unit
            })
        }
    }
    private val scaleShowAnim by lazy {
        ValueAnimator.ofInt(DEFAULT_SIZE, DEFAULT_SIZE * 3).setDuration(300).apply {
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                    btnTop.visibility = VISIBLE
                }

                override fun onAnimationEnd(animation: Animator?) {
                    btnTop.text = topContent
                }

                override fun onAnimationCancel(animation: Animator?) = Unit

                override fun onAnimationRepeat(animation: Animator?) = Unit
            })
            addUpdateListener {
                val i = it.animatedValue as Int
                btnTop.layoutParams.width = i
                btnTop.width = i
            }
        }
    }
    private val scaleHideAnim by lazy {
        ValueAnimator.ofInt(DEFAULT_SIZE * 3, DEFAULT_SIZE).setDuration(300).apply {
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                    btnTop.text = null
                }

                override fun onAnimationEnd(animation: Animator?) {
                    btnTop.visibility = GONE
                }

                override fun onAnimationCancel(animation: Animator?) = Unit

                override fun onAnimationRepeat(animation: Animator?) = Unit
            })
            addUpdateListener {
                val i = it.animatedValue as Int
                btnTop.layoutParams.width = i
                btnTop.width = i
            }
        }
    }
    /************************    动画    ************************/

    /**
     * 是否展开，实现动画
     */
    private var expanded = false
        set(value) {
            if (field != value) {
                field = value
                if (!showAnimation) return
                if (showTopContent) btnTop.clearAnimation()
                btnSub.clearAnimation()
                btnAdd.clearAnimation()
                if (field) {
                    if (showTopContent) scaleHideAnim.start()
                    btnSub.startAnimation(subShowAnim)
                    btnAdd.startAnimation(addShowAnim)
                } else {
                    if (showTopContent) scaleShowAnim.start()
                    btnSub.startAnimation(subHideAnim)
                    btnAdd.startAnimation(addHideAnim)
                }
            }
        }

    private var showTopContent: Boolean = true
    private var showAnimation: Boolean = true
    private var topContent: String? = null

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

    private val btnTop: TextView = TextView(context).apply {
        layoutParams = LayoutParams(DEFAULT_SIZE * 3, DEFAULT_SIZE).apply { gravity = Gravity.END }
        text = topContent
        textSize = 12F
        gravity = Gravity.CENTER
        setTextColor(Color.WHITE)
        val radiusArray = floatArrayOf(100F, 100F, 100F, 100F, 100F, 100F, 100F, 100F)
        background = ShapeDrawable(RoundRectShape(radiusArray, null, null)).apply {
            paint.color = Color.parseColor("#A6B620")
        }
        setOnClickListener { btnAdd.performClick() }
    }
    override val btnSub: View = AppCompatImageView(context).apply {
        layoutParams = LayoutParams(DEFAULT_SIZE, DEFAULT_SIZE)
        setImageResource(R.drawable.selector_num_sub)
        setOnClickListener {
            val temp = currentNumber - 1
            if (shouldInterceptSub(temp)) return@setOnClickListener else currentNumber = temp
        }
    }
    override val btnAdd: View = AppCompatImageView(context).apply {
        elevation = 1F
        layoutParams = LayoutParams(DEFAULT_SIZE, DEFAULT_SIZE)
        setImageResource(R.drawable.selector_num_add)
        setOnClickListener {
            val temp = currentNumber + 1
            if (shouldInterceptAdd(temp)) return@setOnClickListener else currentNumber = temp
        }
    }
    override val viewNumber: TextView = TextView(context).apply {
        layoutParams = LayoutParams(DEFAULT_SIZE, DEFAULT_SIZE)
        textSize = 12F
        text = "0"
        gravity = Gravity.CENTER
        setTextColor(Color.BLACK)
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.AnimationNumberView).use {
            showTopContent = it.getBoolean(
                R.styleable.AnimationNumberView_showTopContent, showTopContent
            )
            topContent = it.getString(R.styleable.AnimationNumberView_topContent)
            showAnimation =
                it.getBoolean(R.styleable.AnimationNumberView_showAnimation, showAnimation)
        }
        addView(LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            addView(btnSub)
            addView(viewNumber)
            addView(btnAdd)
            btnSub.visibility = INVISIBLE
            viewNumber.visibility = INVISIBLE
        })
        addView(btnTop)

        if (!showTopContent) btnTop.visibility = GONE else btnTop.text = topContent
    }

    override fun shouldInterceptAdd(afterChange: Int): Boolean {
        val over = afterChange > maxNumber
        return callback?.shouldIntercept(afterChange, 1) ?: true && !over
    }

    override fun shouldInterceptSub(afterChange: Int): Boolean {
        val over = afterChange < 0
        return callback?.shouldIntercept(afterChange, 2) ?: true && !over
    }
    fun setChangeNumber(callback:OnNumberChangeListener){
        this.callback = callback
    }
    override fun afterNumberChange(current: Int) {
        if (current > 0) expanded = true
        val shouldExpand = current == 1 && !expanded
        val shouldShrink = current == 0 && expanded
        if (shouldExpand || shouldShrink) expanded = !expanded
        btnAdd.isEnabled = current + 1 <= maxNumber
        btnSub.isEnabled = current - 1 >= 0
    }
}