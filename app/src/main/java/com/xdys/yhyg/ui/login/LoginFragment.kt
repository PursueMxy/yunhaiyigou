package com.xdys.yhyg.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hjq.toast.ToastUtils
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.kit.span.ClickMovementMethod
import com.xdys.library.kit.span.CustomClickSpan
import com.xdys.library.utils.mxyUtils
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentLoginBinding
import com.xdys.yhyg.ui.home.MainActivity
import com.xdys.yhyg.vm.LoginViewModel

class LoginFragment : ViewModelFragment<LoginViewModel, FragmentLoginBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(layoutInflater)

    private val navController by lazy { findNavController() }

    override val viewModel: LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        tvMobileVerificationLogin.setOnClickListener {
            navController.navigate(R.id.loginCaptchaFragment)
        }
        tvTitle.setOnRightClickListener { navController.navigate(R.id.registerFragment) }
        btnLogin.setOnClickListener {
            if (tvLoginAgree.isSelected) {
                login()
            } else {
                ToastUtils.show("请同意用户协议")
            }
        }
        with(tvLoginAgree) {
            movementMethod = ClickMovementMethod.getInstance()
            text = buildSpannedString {
                append(getString(R.string.login_agree_1))
                inSpans(CustomClickSpan({
                })) { append(getString(R.string.user_agreement)) }
            }
            setOnClickListener {
                tvLoginAgree.isSelected = !tvLoginAgree.isSelected
            }
        }
        etMobile.doAfterTextChanged { changeLogin() }
        etPassword.doAfterTextChanged { changeLogin() }
    }

    override fun initObserver() {
        viewModel.loginLiveData.observe(this) {
            MainActivity.startActivity(requireContext(), true)
        }
    }


    fun login() {
        with(binding) {
            val mobile = etMobile.text.toString().trim()
            if (mobile.isEmpty()) {
                ToastUtils.show(etMobile.hint.toString())
                return
            }
            val password = etPassword.text.toString().trim()
            if (password.isEmpty()) {
                ToastUtils.show(etPassword.hint.toString())
                return
            }
            viewModel.login(mobile, mxyUtils.MD5Utils.digest(password))
        }
    }

    private fun changeLogin() {
        with(binding) {
            if (etMobile.text.length == 11 && etPassword.text.length > 4) {
                btnLogin.isEnabled = true
                btnLogin.alpha = 1f
            } else {
                btnLogin.isEnabled = false
                btnLogin.alpha = 0.4f
            }
        }
    }
}