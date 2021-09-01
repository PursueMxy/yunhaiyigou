package com.xdys.yhyg.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.xdys.yhyg.databinding.FragmentConfirmPaymentPasswordBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class ConfirmPaymentPasswordFragment :
    ViewModelFragment<MineViewModel, FragmentConfirmPaymentPasswordBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentConfirmPaymentPasswordBinding.inflate(inflater, container, false)

    private val navController by lazy { findNavController() }
    override val viewModel: MineViewModel by activityViewModels()
}