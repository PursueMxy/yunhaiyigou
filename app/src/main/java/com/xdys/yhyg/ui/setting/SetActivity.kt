package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivitySetBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class SetActivity : ViewModelActivity<MineViewModel, ActivitySetBinding>() {
    override fun createBinding() = ActivitySetBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SetActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        clPersonal.setOnClickListener {
            PersonalInformationActivity.start(this@SetActivity)
        }
        tvAccountSecurity.setOnClickListener {
            AccountSecurityActivity.start(this@SetActivity)
        }
    }
}