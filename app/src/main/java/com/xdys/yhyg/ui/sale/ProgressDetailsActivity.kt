package com.xdys.yhyg.ui.sale

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.adapte.sale.ProgressDetailsAdapter
import com.xdys.yhyg.databinding.ActivityProgressDetailsBinding
import com.xdys.yhyg.vm.AfterSaleViewModel

class ProgressDetailsActivity :
    ViewModelActivity<AfterSaleViewModel, ActivityProgressDetailsBinding>() {
    override fun createBinding() = ActivityProgressDetailsBinding.inflate(layoutInflater)

    override val viewModel: AfterSaleViewModel by viewModels()

    private val mAdpter by lazy { ProgressDetailsAdapter() }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ProgressDetailsActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvAfterSale) {
            adapter = mAdpter
        }
    }

    override fun initData() {
        mAdpter.setNewInstance(mutableListOf("", "", ""))
    }
}