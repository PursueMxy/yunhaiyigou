package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import com.xdys.yhyg.R
import razerdp.basepopup.BasePopupWindow

class SharePopupWindow(context: Context, private val confirm: ((position: Int) -> Unit)? = null) :
    BasePopupWindow(context) {
     fun onCreateContentView(): View = createPopupById(R.layout.popup_share)
    override fun onViewCreated(contentView: View) {
        findViewById<View>(R.id.tvWeChatFriends).setOnClickListener {
            confirm?.invoke(0)
            dismiss()
        }
        findViewById<View>(R.id.tvCircleFriends).setOnClickListener {
            confirm?.invoke(1)
            dismiss()
        }
        findViewById<View>(R.id.tvShareQQ).setOnClickListener {
            confirm?.invoke(2)
            dismiss()
        }
        findViewById<View>(R.id.tvLink).setOnClickListener {
            confirm?.invoke(3)
            dismiss()
        }
        findViewById<View>(R.id.tvCancel).setOnClickListener {
            dismiss()
        }
    }

}
