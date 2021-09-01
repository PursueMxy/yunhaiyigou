package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.databinding.ActivityDeviceDetailBinding
import com.xdys.yhyg.vm.MineViewModel

class DeviceDetailActivity : ViewModelActivity<MineViewModel, ActivityDeviceDetailBinding>() {
    override fun createBinding() = ActivityDeviceDetailBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, DeviceDetailActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        flDeviceName.setOnClickListener {
            ModifyDeviceNameActivity.start(this@DeviceDetailActivity)
        }
    }
}