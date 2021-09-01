package com.xdys.yhyg.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentChangeMobileCurrentBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class ChangeMobileCurrentFragment :
    ViewModelFragment<MineViewModel, FragmentChangeMobileCurrentBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChangeMobileCurrentBinding.inflate(inflater, container, false)

    private val navController by lazy { findNavController() }

    override val viewModel: MineViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvNextStep.setOnClickListener {
            navController.navigate(R.id.changeMobileFragment)
        }
    }
}