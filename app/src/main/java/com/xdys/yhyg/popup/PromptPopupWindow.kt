package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import android.widget.TextView
import com.xdys.yhyg.R
import razerdp.basepopup.BasePopupWindow

class PromptPopupWindow(
    context: Context, private val confirm: () -> Unit,
) : BasePopupWindow(context) {

    override fun onCreateContentView(): View = createPopupById(R.layout.popup_prompt)
    override fun onViewCreated(contentView: View) {
        findViewById<View>(R.id.tvCancel).setOnClickListener {
            dismiss()
        }
        findViewById<View>(R.id.tvConfirm).setOnClickListener {
            dismiss()
            confirm?.invoke()
        }
    }


    fun setData(title: String): PromptPopupWindow {
        findViewById<TextView>(R.id.tvTitle).text = title
        return this
    }

}