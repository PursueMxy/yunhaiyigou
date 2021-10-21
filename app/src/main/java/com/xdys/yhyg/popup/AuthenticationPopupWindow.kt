package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import com.xdys.yhyg.R
import razerdp.basepopup.BasePopupWindow

class AuthenticationPopupWindow(
    context: Context, private val confirm: () -> Unit,
) : BasePopupWindow(context) {

    fun onCreateContentView(): View = createPopupById(R.layout.popup_authentication)
    override fun onViewCreated(contentView: View) {
        findViewById<View>(R.id.ivClose).setOnClickListener {
            dismiss()
        }

    }
}