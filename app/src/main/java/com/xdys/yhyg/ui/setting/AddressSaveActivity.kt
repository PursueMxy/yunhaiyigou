package com.xdys.yhyg.ui.setting

import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityAddressSaveBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity

class AddressSaveActivity : ViewModelActivity<MineViewModel, ActivityAddressSaveBinding>() {
    override fun createBinding() = ActivityAddressSaveBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()
}