package com.xdys.yhyg.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hjq.toast.ToastUtils
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.kit.span.ClickMovementMethod
import com.xdys.library.kit.span.CustomClickSpan
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentRegisterBinding
import com.xdys.yhyg.vm.LoginViewModel

class RegisterFragment : ViewModelFragment<LoginViewModel, FragmentRegisterBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override val viewModel: LoginViewModel by activityViewModels()

    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        with(tvLoginAgree) {
            movementMethod = ClickMovementMethod.getInstance()
            text = buildSpannedString {
                append(getString(R.string.privacy_agree))
                inSpans(CustomClickSpan({
                }, R.color.BL0099)) { append(getString(R.string.privacy_policy)) }
            }
            setOnClickListener {
                tvLoginAgree.isSelected = !tvLoginAgree.isSelected
            }
        }
        btnSingUpNow.setOnClickListener {
            if (tvLoginAgree.isSelected) {
                register()
            } else {
                ToastUtils.show("请同意注册协议")
            }
        }

        tvAlreadyLogged.setOnClickListener {
            navController.navigate(R.id.loginFragment)
        }
        tvGetCode.setOnClickListener {
            sendCode()
        }

        with(tvLoginAgree) {
            movementMethod = ClickMovementMethod.getInstance()
            text = buildSpannedString {
                append(getString(R.string.login_agree_1))
                inSpans(CustomClickSpan({
                })) { append(getString(R.string.user_agreement)) }
            }
        }

        etMobile.doAfterTextChanged {
            changeButton()
            changeLogin()
        }
        etVerificationCode.doAfterTextChanged {
            changeLogin()
        }
        etPassword.doAfterTextChanged {
            changeLogin()
        }
        etEnterPassword.doAfterTextChanged {
            changeLogin()
        }
    }

    override fun initObserver() {
        viewModel.countdownLiveData.observe(viewLifecycleOwner, {
            if (it > 0) {
                binding.tvGetCode.isEnabled = false
                binding.tvGetCode.text = getString(R.string.login_resend_format, it)
            } else {
                binding.tvGetCode.isEnabled = true
                binding.tvGetCode.text = buildSpannedString {
                    append(getString(R.string.get_verification_code))
                }
            }
        })
        viewModel.registerLiveData.observe(this) {
            navController.navigate(R.id.loginFragment)
        }
    }

    fun register() {
        with(binding) {
            var mobile = etMobile.text.toString()
            if (mobile.isEmpty()) {
                ToastUtils.show(etMobile.hint.toString())
                return
            }
            var code = etVerificationCode.text.toString()
            if (code.isEmpty()) {
                ToastUtils.show("验证码不能为空")
                return
            }
            var password = etPassword.text.toString()
            if (password.isEmpty()) {
                ToastUtils.show(etPassword.hint.toString())
                return
            }
            var enterPassword = etEnterPassword.text.toString()
            if (password != enterPassword) {
                ToastUtils.show("两次密码输入不一致")
                return
            }
            viewModel.register(mobile, code, password)
        }
    }

    fun sendCode() {
        with(binding) {
            var mobile = etMobile.text.toString()
            if (mobile.isEmpty()) {
                ToastUtils.show(etMobile.hint.toString())
                return
            }
            if (mobile.length != 11) {
                return
            }
            viewModel.sendRegisterSms(mobile, true)
        }
    }

    private fun changeButton() {
        with(binding) {
            if (etMobile.text.length == 11) {
                tvGetCode.isEnabled = true
                tvGetCode.alpha = 1f
            } else {
                tvGetCode.isEnabled = false
                tvGetCode.alpha = 0.4f
            }
        }
    }

    private fun changeLogin() {
        with(binding) {
            if (etMobile.text.length == 11 && etVerificationCode.text.length == 6
                && etPassword.text.length > 4 && etEnterPassword.text.length > 4) {
                btnSingUpNow.isEnabled = true
                btnSingUpNow.alpha = 1f
            } else {
                btnSingUpNow.isEnabled = false
                btnSingUpNow.alpha = 0.4f
            }
        }
    }
}