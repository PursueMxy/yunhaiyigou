package com.xdys.yhyg.ui.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.adapte.mine.TransactionAdapter
import com.xdys.yhyg.databinding.ActivityTransactionNotificationBinding
import com.xdys.yhyg.entity.mine.TransactionEntity
import com.xdys.yhyg.vm.MineViewModel

class TransactionNotificationActivity :
    ViewModelActivity<MineViewModel, ActivityTransactionNotificationBinding>() {

    override fun createBinding() = ActivityTransactionNotificationBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, TransactionNotificationActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val mAdapter by lazy { TransactionAdapter() }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvTransaction) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf(TransactionEntity()))
    }
}