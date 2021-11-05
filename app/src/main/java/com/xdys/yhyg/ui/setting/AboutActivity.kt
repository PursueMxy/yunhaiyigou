package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.ActivityAboutBinding
import com.xdys.yhyg.vm.SetViewModel

class AboutActivity : ViewModelActivity<SetViewModel, ActivityAboutBinding>() {
    override fun createBinding() = ActivityAboutBinding.inflate(layoutInflater)

    override val viewModel: SetViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AboutActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) {
        binding.tvVersionName.text = getString(R.string.version_name) + "V.1.0.1"
        super.initUI(savedInstanceState)
    }
}

