package com.xdys.yhyg.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hjq.toast.ToastUtils
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentLoginBinding
import com.xdys.yhyg.vm.LoginViewModel

class LoginFragment : ViewModelFragment<LoginViewModel, FragmentLoginBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(layoutInflater)

    private val navController by lazy { findNavController() }

    override val viewModel: LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvMobileVerificationLogin.setOnClickListener {
            navController.navigate(R.id.loginCaptchaFragment)
        }
        tvTitle.setOnRightClickListener { navController.navigate(R.id.registerFragment) }
        btnLogin.setOnClickListener {
//            MainActivity.startActivity(
//                requireContext(), false
//            )
            viewModel.login(etMobile.text.toString(), etPassword.text.toString())
        }
    }

    override fun initObserver() {
        viewModel.loginLiveData.observe(this) {
            ToastUtils.show(it)
        }
    }
}