package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.databinding.ActivityModifyDeviceNameBinding
import com.xdys.yhyg.vm.MineViewModel

class ModifyDeviceNameActivity :
    ViewModelActivity<MineViewModel, ActivityModifyDeviceNameBinding>() {
    override fun createBinding() = ActivityModifyDeviceNameBinding.inflate(layoutInflater)
    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ModifyDeviceNameActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding){
    }
}