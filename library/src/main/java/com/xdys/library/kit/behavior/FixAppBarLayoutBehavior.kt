package com.xdys.library.kit.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.OverScroller
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class FixAppBarLayoutBehavior @JvmOverloads constructor(
    context: Context? = null,
    attrs: AttributeSet? = null
) : AppBarLayout.Behavior(context, attrs) {

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout, child: AppBarLayout, ev: MotionEvent
    ): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val scroller = getSuperSuperField(this, "scroller")
            if (scroller is OverScroller) scroller.abortAnimation()
        }
        return super.onInterceptTouchEvent(parent, child, ev)
    }

    private fun getSuperSuperField(paramClass: Any, paramString: String): Any? {
        return try {
            val field =
                paramClass.javaClass.superclass.superclass.superclass.getDeclaredField(paramString)
            field.isAccessible = true
            field.get(paramClass)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}