package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import android.widget.TextView
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.PopupPromptBinding
import razerdp.basepopup.BasePopupWindow

class PromptPopupWindow(
    context: Context, private val confirm: () -> Unit,
) : BasePopupWindow(context) {

    private lateinit var binding: PopupPromptBinding

    init {
        contentView = createPopupById(R.layout.popup_prompt)
    }

    override fun onViewCreated(contentView: View) {
        binding = PopupPromptBinding.bind(contentView)
        binding.tvCancel.setOnClickListener {
            dismiss()
        }
        binding.tvConfirm.setOnClickListener {
            dismiss()
            confirm?.invoke()
        }
    }


    fun setData(title: String): PromptPopupWindow {
        binding.tvTitle.text = title
        return this
    }

}