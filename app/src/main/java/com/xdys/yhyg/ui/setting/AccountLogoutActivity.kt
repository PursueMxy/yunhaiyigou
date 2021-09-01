package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.databinding.ActivityAccountLogoutBinding
import com.xdys.yhyg.vm.MineViewModel

class AccountLogoutActivity : ViewModelActivity<MineViewModel, ActivityAccountLogoutBinding>() {
    override fun createBinding() = ActivityAccountLogoutBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AccountLogoutActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
    }
}