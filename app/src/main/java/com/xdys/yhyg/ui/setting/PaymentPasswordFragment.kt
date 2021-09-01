package com.xdys.yhyg.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentPaymentPasswordBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class PaymentPasswordFragment : ViewModelFragment<MineViewModel, FragmentPaymentPasswordBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPaymentPasswordBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()
    private val navController by lazy { findNavController() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvChangePsw.setOnClickListener {
            navController.navigate(R.id.setPaymentPasswordFragment)
        }
    }
}