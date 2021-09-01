package com.xdys.yhyg.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.xdys.yhyg.databinding.FragmentChangeMobileCurrentBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.R

class ChangeLoginPswFragment :
    ViewModelFragment<MineViewModel, FragmentChangeMobileCurrentBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChangeMobileCurrentBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by activityViewModels()

    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvNextStep.setOnClickListener { navController.navigate(R.id.changeLoginPswSetFragment) }
    }
}