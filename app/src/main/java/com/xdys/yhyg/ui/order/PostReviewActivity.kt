package com.xdys.yhyg.ui.order

import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityPostReviewBinding
import com.xdys.yhyg.vm.OrderViewModel
import com.xdys.library.base.ViewModelActivity

class PostReviewActivity : ViewModelActivity<OrderViewModel, ActivityPostReviewBinding>() {
    override fun createBinding() = ActivityPostReviewBinding.inflate(layoutInflater)

    override val viewModel: OrderViewModel by viewModels()

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {

    }
}