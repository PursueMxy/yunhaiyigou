package com.xdys.yhyg.ui.setting

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.hjq.toast.ToastUtils
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.config.Constant
import com.xdys.yhyg.databinding.FragmentChangeLoginPswSetBinding
import com.xdys.yhyg.ui.login.LoginActivity
import com.xdys.yhyg.vm.SetViewModel

class ChangeLoginPswSetFragment :
    ViewModelFragment<SetViewModel, FragmentChangeLoginPswSetBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChangeLoginPswSetBinding.inflate(inflater, container, false)

    override val viewModel: SetViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvConfirm.setOnClickListener {
            val password = etPsw.text.toString()
            if (password.isEmpty() && password.length > 4) {
                ToastUtils.show("密码至少5位")
                return@setOnClickListener
            } else {
                viewModel.restLoginPwd(
                    Constant.mobile.toString(),
                    viewModel.smsCode.toString(),
                    password
                )
            }
        }
        ivHidden.setOnClickListener {
            it.isSelected = !it.isSelected
            if (it.isSelected) etPsw.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            else etPsw.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            etPsw.setSelection(etPsw.text.length)
        }
    }

    override fun initObserver() {
        viewModel.restLoginPwdLiveData.observe(this) {
            LoginActivity.startActivity(requireContext(), true)
        }
    }

}