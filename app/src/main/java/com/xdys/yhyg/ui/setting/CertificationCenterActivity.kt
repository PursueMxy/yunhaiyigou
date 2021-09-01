package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityCertificationCenterBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class CertificationCenterActivity :
    ViewModelActivity<MineViewModel, ActivityCertificationCenterBinding>() {

    override fun createBinding() = ActivityCertificationCenterBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CertificationCenterActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }
}