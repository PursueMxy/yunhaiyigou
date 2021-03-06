package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityChangeNicknameBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant.Key.EXTRA_ID
import com.xdys.library.config.Constant.Key.EXTRA_TITLE
import com.xdys.library.extension.singleTop

class ChangeNicknameActivity : ViewModelActivity<MineViewModel, ActivityChangeNicknameBinding>() {
    override fun createBinding() = ActivityChangeNicknameBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ChangeNicknameActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
    }
}
