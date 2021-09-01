package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityPersonalInformationBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class PersonalInformationActivity :
    ViewModelActivity<MineViewModel, ActivityPersonalInformationBinding>() {
    override fun createBinding() = ActivityPersonalInformationBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, PersonalInformationActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        flNickName.setOnClickListener {
            ChangeNicknameActivity.start(this@PersonalInformationActivity)
        }
    }
}