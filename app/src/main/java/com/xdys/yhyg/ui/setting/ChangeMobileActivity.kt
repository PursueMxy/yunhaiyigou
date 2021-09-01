package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.ActivityChangeMobileBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class ChangeMobileActivity : ViewModelActivity<MineViewModel, ActivityChangeMobileBinding>() {
    override fun createBinding() = ActivityChangeMobileBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ChangeMobileActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }
    private val navController by lazy { findNavController(R.id.changeMobileContainer) }
}