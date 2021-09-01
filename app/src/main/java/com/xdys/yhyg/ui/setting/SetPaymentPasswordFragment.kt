package com.xdys.yhyg.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentSetPaymentPasswordBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.widget.code.VerificationCodeView

class SetPaymentPasswordFragment :
    ViewModelFragment<MineViewModel, FragmentSetPaymentPasswordBinding>(),
    VerificationCodeView.OnCodeCompleteListener {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSetPaymentPasswordBinding.inflate(inflater, container, false)

    private val navController by lazy { findNavController() }
    override val viewModel: MineViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {


    }

    override fun complete(content: String) {
        navController.navigate(R.id.confirmPaymentPasswordFragment)
    }
}