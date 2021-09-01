package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.databinding.ActivityAccountSecurityBinding
import com.xdys.yhyg.vm.MineViewModel

class AccountSecurityActivity : ViewModelActivity<MineViewModel, ActivityAccountSecurityBinding>() {
    override fun createBinding() = ActivityAccountSecurityBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AccountSecurityActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        clVerified.setOnClickListener {
            AuthenticationActivity.start(this@AccountSecurityActivity)
        }
        clMobile.setOnClickListener {
            ChangeMobileActivity.start(this@AccountSecurityActivity)
        }
        clPaymentPassword.setOnClickListener {
            ChangePaymentPasswordActivity.start(this@AccountSecurityActivity)
        }
        clModifyLoginPassword.setOnClickListener {
            ChangeLoginPswActivity.start(this@AccountSecurityActivity)
        }
        clEquipmentManagement.setOnClickListener {
            EquipmentManagementActivity.start(this@AccountSecurityActivity)
        }
        clAccountSecurity.setOnClickListener {
            AccountLogoutActivity.start(this@AccountSecurityActivity)
        }

    }
}