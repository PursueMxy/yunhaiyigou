package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityPaymentPasswordBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class PaymentPasswordActivity : ViewModelActivity<MineViewModel, ActivityPaymentPasswordBinding>() {
    override fun createBinding() = ActivityPaymentPasswordBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, PaymentPasswordActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }
}