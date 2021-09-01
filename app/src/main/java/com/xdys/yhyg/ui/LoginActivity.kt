package com.xdys.yhyg.ui

import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityLoginBinding
import com.xdys.yhyg.vm.LoginViewModel
import com.xdys.library.base.ViewModelActivity

class LoginActivity : ViewModelActivity<LoginViewModel, ActivityLoginBinding>() {
    override fun createBinding(): ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)

    override val viewModel: LoginViewModel by viewModels()
}