package com.xdys.yhyg.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentLoginCaptchaBinding
import com.xdys.yhyg.vm.LoginViewModel

class LoginCaptchaFragment : ViewModelFragment<LoginViewModel, FragmentLoginCaptchaBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginCaptchaBinding.inflate(layoutInflater)

    override val viewModel: LoginViewModel by viewModels()

    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvAccountPasswordLogin.setOnClickListener {
            navController.navigate(R.id.loginCaptchaFragment)
        }
    }
}