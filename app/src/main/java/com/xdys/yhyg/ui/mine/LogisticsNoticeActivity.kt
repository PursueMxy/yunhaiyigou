package com.xdys.yhyg.ui.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.adapte.mine.LogisticsAdapter
import com.xdys.yhyg.databinding.ActivityLogisticsNoticeBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class LogisticsNoticeActivity : ViewModelActivity<MineViewModel, ActivityLogisticsNoticeBinding>() {
    override fun createBinding() = ActivityLogisticsNoticeBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    private val mAdapter by lazy { LogisticsAdapter() }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LogisticsNoticeActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvLogistics) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf("", "", ""))
    }
}