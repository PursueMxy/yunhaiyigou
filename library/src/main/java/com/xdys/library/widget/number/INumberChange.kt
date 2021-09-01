package com.xdys.library.widget.number

import android.view.View
import android.widget.TextView

interface INumberChange {

    /**
     * 最大值
     */
    var maxNumber: Int

    /**
     * 当前值
     */
    var currentNumber: Int

    val btnSub: View
    val btnAdd: View
    val viewNumber: TextView

    /**
     * 数量增加之前的回调
     * @return 是否需要拦截此次变化
     */
    fun shouldInterceptAdd(afterChange: Int): Boolean

    /**
     * 数量减少之前的回调
     * @return 是否需要拦截此次变化
     */
    fun shouldInterceptSub(afterChange: Int): Boolean

    /**
     * 数量变化的回调
     */
    fun afterNumberChange(current: Int)
}

interface OnNumberChangeListener {
    fun shouldIntercept(afterChange: Int, operationType: Int): Boolean
}