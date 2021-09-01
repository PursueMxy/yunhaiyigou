package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityChangePaymentPasswordBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class ChangePaymentPasswordActivity :
    ViewModelActivity<MineViewModel, ActivityChangePaymentPasswordBinding>() {
    override fun createBinding() = ActivityChangePaymentPasswordBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ChangePaymentPasswordActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        tvNextStep.setOnClickListener {
            PaymentPasswordActivity.start(this@ChangePaymentPasswordActivity
            )
        }
    }
}