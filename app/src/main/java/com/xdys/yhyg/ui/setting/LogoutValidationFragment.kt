package com.xdys.yhyg.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentValidationLogoutBinding
import com.xdys.yhyg.vm.MineViewModel

class LogoutValidationFragment :
    ViewModelFragment<MineViewModel, FragmentValidationLogoutBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentValidationLogoutBinding.inflate(inflater, container, false)

    private val navController by lazy { findNavController() }

    override val viewModel: MineViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvNextStep.setOnClickListener {
            navController.navigate(R.id.confirmLogoutFragment)
        }
    }
}