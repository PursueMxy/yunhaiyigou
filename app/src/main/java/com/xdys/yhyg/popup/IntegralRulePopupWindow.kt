package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.xdys.yhyg.R
import razerdp.basepopup.BasePopupWindow

class IntegralRulePopupWindow(
    context: Context, private val confirm: () -> Unit,
) : BasePopupWindow(context) {

    fun onCreateContentView(): View = createPopupById(R.layout.popup_integral_rule)
    override fun onViewCreated(contentView: View) {
        findViewById<ImageView>(R.id.ivClose).setOnClickListener {
            dismiss()
        }

    }
}
