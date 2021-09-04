package com.xdys.yhyg.ui.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.databinding.ActivityWithdrawalBinding
import com.xdys.yhyg.popup.WithdrawalAmountPopupWindow
import com.xdys.yhyg.popup.WithdrawalInstructionsPopubWindow
import com.xdys.yhyg.vm.MineViewModel

class WithdrawalActivity : ViewModelActivity<MineViewModel, ActivityWithdrawalBinding>() {
    override fun createBinding() = ActivityWithdrawalBinding.inflate(layoutInflater)

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, WithdrawalActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override val viewModel: MineViewModel by viewModels()


    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        clPayType.setOnClickListener {
            popupWithdrawalAmount.showPopupWindow()
        }
        titleBar.setOnRightClickListener {
            popupWithdrawalInstructions.showPopupWindow()
        }
    }

    private val popupWithdrawalAmount: WithdrawalAmountPopupWindow by lazy {
        WithdrawalAmountPopupWindow(this) {
        }
    }

    private val popupWithdrawalInstructions: WithdrawalInstructionsPopubWindow by lazy {
        WithdrawalInstructionsPopubWindow(this) {
        }
    }
}