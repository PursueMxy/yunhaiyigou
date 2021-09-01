package com.xdys.yhyg.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.xdys.yhyg.databinding.FragmentChangeLoginPswSetBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class ChangeLoginPswSetFragment :
    ViewModelFragment<MineViewModel, FragmentChangeLoginPswSetBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChangeLoginPswSetBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()
}