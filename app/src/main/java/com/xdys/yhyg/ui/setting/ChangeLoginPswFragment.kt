package com.xdys.yhyg.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.buildSpannedString
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hjq.toast.ToastUtils
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.config.Constant
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentChangeMobileCurrentBinding
import com.xdys.yhyg.vm.LoginViewModel
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.yhyg.vm.SetViewModel

class ChangeLoginPswFragment :
    ViewModelFragment<SetViewModel, FragmentChangeMobileCurrentBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChangeMobileCurrentBinding.inflate(layoutInflater)

    override val viewModel: SetViewModel by activityViewModels()

    val loginViewModel by lazy { LoginViewModel() }

    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvNextStep.setOnClickListener {
            var code = etAuthCode.text.toString()
            if (code.length == 6) {
                viewModel.checkSmsCode(Constant.mobile.toString(), code)
            } else {
                ToastUtils.show("请输入6位数验证码")
                return@setOnClickListener
            }
        }
        btnGetVerificationCode.setOnClickListener {
            loginViewModel.sendRegisterSms(Constant.mobile.toString(), "7")
        }
        tvInput.text = "请输入${Constant.mobile}收到的短信验证码"
    }

    override fun initObserver() {
        viewModel.checkSmsCodeLiveData.observe(viewLifecycleOwner) {
            navController.navigate(R.id.changeLoginPswSetFragment)
        }
        loginViewModel.countdownLiveData.observe(viewLifecycleOwner, {
            if (it > 0) {
                binding.btnGetVerificationCode.isEnabled = false
                binding.btnGetVerificationCode.text = getString(R.string.login_resend_format, it)
            } else {
                binding.btnGetVerificationCode.isEnabled = true
                binding.btnGetVerificationCode.text = buildSpannedString {
                    append(getString(R.string.get_verification_code))
                }
            }
        })
    }

}