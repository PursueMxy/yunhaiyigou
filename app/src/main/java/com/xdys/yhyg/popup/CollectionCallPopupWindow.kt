package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import android.widget.TextView
import com.xdys.yhyg.R
import razerdp.basepopup.BasePopupWindow

class CollectionCallPopupWindow(
    context: Context, private val confirm: () -> Unit,
) : BasePopupWindow(context) {

    fun onCreateContentView(): View = createPopupById(R.layout.popup_collection_call)
    override fun onViewCreated(contentView: View) {
        findViewById<TextView>(R.id.tvDetermine).setOnClickListener {
            confirm?.invoke()
            dismiss()
        }
        findViewById<TextView>(R.id.tvCancel).setOnClickListener {
            dismiss()
        }
    }
}