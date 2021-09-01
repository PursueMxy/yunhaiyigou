package com.xdys.yhyg.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.xdys.yhyg.databinding.FragmentChangeMobileBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class ChangeMobileFragment : ViewModelFragment<MineViewModel, FragmentChangeMobileBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentChangeMobileBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    private val navController by lazy { findNavController() }
}