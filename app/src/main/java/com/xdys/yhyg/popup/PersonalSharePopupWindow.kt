package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import android.widget.TextView
import com.xdys.yhyg.R
import razerdp.basepopup.BasePopupWindow

class PersonalSharePopupWindow(
    context: Context, private val confirm: () -> Unit,
) : BasePopupWindow(context) {

    fun onCreateContentView(): View = createPopupById(R.layout.popup_personal_share)
    override fun onViewCreated(contentView: View) {
        findViewById<TextView>(R.id.tvNickName).text="醉饮三百杯(ID:201110222)"
        findViewById<TextView>(R.id.tvTitle).text="省钱购物 分享赚钱"
        findViewById<View>(R.id.ivClose).setOnClickListener {
            dismiss()
        }
    }
}