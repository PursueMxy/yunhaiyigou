package com.xdys.yhyg.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.king.view.splitedittext.SplitEditText
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentSetPaymentPasswordBinding
import com.xdys.yhyg.vm.MineViewModel

class SetPaymentPasswordFragment :
    ViewModelFragment<MineViewModel, FragmentSetPaymentPasswordBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSetPaymentPasswordBinding.inflate(inflater, container, false)

    private val navController by lazy { findNavController() }
    override val viewModel: MineViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        //设置监听
        splitEditText.setOnTextInputListener(object : SplitEditText.OnTextInputListener {
            override fun onTextInputChanged(text: String, length: Int) {
                if (length == 6) {
                    navController.navigate(R.id.confirmPaymentPasswordFragment)
                }
            }

            override fun onTextInputCompleted(text: String) {
                //TODO 文本输入完成
            }

        })


    }

}