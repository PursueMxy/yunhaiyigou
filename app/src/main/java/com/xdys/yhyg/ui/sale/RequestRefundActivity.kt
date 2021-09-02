package com.xdys.yhyg.ui.sale

import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.yhyg.databinding.ActivityRequestRefundBinding
import com.xdys.yhyg.vm.AfterSaleViewModel

class RequestRefundActivity :
    ViewModelActivity<AfterSaleViewModel, ActivityRequestRefundBinding>() {
    override fun createBinding() = ActivityRequestRefundBinding.inflate(layoutInflater)

    override val viewModel: AfterSaleViewModel by viewModels()

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {

    }
}