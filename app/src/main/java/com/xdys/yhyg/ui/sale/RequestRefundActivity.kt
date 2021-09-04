package com.xdys.yhyg.ui.sale

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant.Key.EXTRA_TITLE
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.databinding.ActivityRequestRefundBinding
import com.xdys.yhyg.vm.AfterSaleViewModel

class RequestRefundActivity :
    ViewModelActivity<AfterSaleViewModel, ActivityRequestRefundBinding>() {
    override fun createBinding() = ActivityRequestRefundBinding.inflate(layoutInflater)

    override val viewModel: AfterSaleViewModel by viewModels()

    companion object {
        fun start(context: Context, title: String) {
            val intent = Intent(context, RequestRefundActivity::class.java)
                .putExtra(EXTRA_TITLE, title)
                .singleTop()
            context.startActivity(intent)
        }
    }
    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        titleBar.titleContent = intent.getStringExtra(EXTRA_TITLE)
    }
}