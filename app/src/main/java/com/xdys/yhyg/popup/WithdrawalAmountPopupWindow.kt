package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import com.xdys.yhyg.R
import razerdp.basepopup.BasePopupWindow

class WithdrawalAmountPopupWindow(
    context: Context, private val confirm: () -> Unit,
) : BasePopupWindow(context) {

    override fun onCreateContentView(): View = createPopupById(R.layout.popup_withdrawal_amount)
    override fun onViewCreated(contentView: View) {
        findViewById<View>(R.id.ivClose).setOnClickListener {
            dismiss()
        }
    }
}
