package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityAuthenticationBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class AuthenticationActivity : ViewModelActivity<MineViewModel, ActivityAuthenticationBinding>() {

    override fun createBinding() = ActivityAuthenticationBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AuthenticationActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }
}