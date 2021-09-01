package com.xdys.yhyg.ui.setting

import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityPersonalDetailsBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity

class PersonalDetailsActivity : ViewModelActivity<MineViewModel, ActivityPersonalDetailsBinding>() {
    override fun createBinding() = ActivityPersonalDetailsBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()
}