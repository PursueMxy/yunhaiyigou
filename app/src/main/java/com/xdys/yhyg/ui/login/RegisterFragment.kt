package com.xdys.yhyg.ui.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.databinding.FragmentRegisterBinding
import com.xdys.yhyg.vm.LoginViewModel

class RegisterFragment : ViewModelFragment<LoginViewModel, FragmentRegisterBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override val viewModel: LoginViewModel by activityViewModels()
}