package com.xdys.yhyg.ui.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.adapte.mine.FeedbackAdapter
import com.xdys.yhyg.databinding.ActivityFeedbackBinding
import com.xdys.yhyg.entity.mine.FeedbackEntity
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class FeedbackActivity : ViewModelActivity<MineViewModel, ActivityFeedbackBinding>() {
    override fun createBinding() = ActivityFeedbackBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    private val feedbackAdapter by lazy { FeedbackAdapter() }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, FeedbackActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvFeedback) {
            adapter = feedbackAdapter
        }
    }

    override fun initData() {
        feedbackAdapter.setNewInstance(
            mutableListOf(
                FeedbackEntity(false, "Q1:什么时候发货？"),
                FeedbackEntity(false, "Q2:关于售后处理原则"),
                FeedbackEntity(false, "Q3:收货后，发现商品问题如何处理？")
            )
        )
    }
}