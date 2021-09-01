package com.xdys.yhyg.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.databinding.FragmentConfirmLogoutBinding
import com.xdys.yhyg.vm.MineViewModel

class ConfirmLogoutFragment : ViewModelFragment<MineViewModel, FragmentConfirmLogoutBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentConfirmLogoutBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by viewModels()
}