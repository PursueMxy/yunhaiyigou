package com.xdys.library.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.xdys.library.R

/**
 * @description 描述一下方法的作用
 * @date: 2019-07-19 11:18
 */
abstract class BaseBottomDialog<B : ViewBinding>(mContext: Context) : Dialog(
    mContext, R.style.color_dialog
) {
    protected abstract fun init()
    protected lateinit var binding: B
    abstract fun createBinding(): B
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createBinding()
        setContentView(binding.root)
        init()
    }

    override fun onStop() {
        super.onStop()
        dismiss()
    }

    init {
        // 拿到Dialog的Window, 修改Window的属性
        val window = window
        window!!.setWindowAnimations(R.style.PopAnimation)
        window.decorView.setPadding(0, 0, 0, 0)
        //        window.setGravity(Gravity.BOTTOM);
        // 获取Window的LayoutParams
        val attributes = window.attributes
        attributes.width = ViewGroup.LayoutParams.MATCH_PARENT
        attributes.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        // 一定要重新设置, 才能生效
        window.attributes = attributes
    }
}