package com.xdys.yhyg.ui.sale

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.hjq.toast.ToastUtils
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.ActivityViewSolutionBinding
import com.xdys.yhyg.popup.FeedbackPopupWindow
import com.xdys.yhyg.vm.AfterSaleViewModel

class ViewSolutionActivity : ViewModelActivity<AfterSaleViewModel, ActivityViewSolutionBinding>() {
    override fun createBinding() = ActivityViewSolutionBinding.inflate(layoutInflater)

    override val viewModel: AfterSaleViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ViewSolutionActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        tvTitle.text = "商品买错了怎么办？"
        tvSolutionContent.text =
            "您的订单已提交售后服务单，如是18:00前提交申请，一般将在当天22:00前审核完毕，18:00后提交申请，一般将在次日12:00前审核完毕，请您耐心等待并关注服务单审核结果"
        tvSolvedStatus.isSelected = true
        tvSolvedStatus.text = if (tvSolvedStatus.isSelected) resources.getString(R.string.solved)
        else resources.getString(R.string.unsolved)
        tvUnSolved.setOnClickListener {
            popupFeedback.setData("").showPopupWindow()
        }
        tvSolved.setOnClickListener {
            ToastUtils.show("感谢您的反馈！")
            gpSolved.isVisible = false
            tvSolvedStatus.isVisible = true
            tvSolvedStatus.isSelected = true
            tvSolvedStatus.text = resources.getString(R.string.solved)

        }
    }

    private val popupFeedback: FeedbackPopupWindow by lazy {
        FeedbackPopupWindow(this) {
            ToastUtils.show("感谢您的反馈！")
            binding.gpSolved.isVisible = false
            binding.tvSolvedStatus.isVisible = true
            binding.tvSolvedStatus.isSelected = false
            binding.tvSolvedStatus
                .text = resources.getString(R.string.unsolved)
        }
    }
}