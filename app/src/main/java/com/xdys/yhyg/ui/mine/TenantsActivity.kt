package com.xdys.yhyg.ui.mine

import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.yhyg.databinding.ActivityTenantsBinding
import com.xdys.yhyg.vm.MineViewModel

class TenantsActivity : ViewModelActivity<MineViewModel, ActivityTenantsBinding>() {
    override fun createBinding() = ActivityTenantsBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()
}