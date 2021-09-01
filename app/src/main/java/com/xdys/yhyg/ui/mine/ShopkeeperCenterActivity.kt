package com.xdys.yhyg.ui.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.adapte.mine.SalesOrderAdapter
import com.xdys.yhyg.databinding.ActivityShopkeeperCenterBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class ShopkeeperCenterActivity :
    ViewModelActivity<MineViewModel, ActivityShopkeeperCenterBinding>() {
    override fun createBinding() = ActivityShopkeeperCenterBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ShopkeeperCenterActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val salesOrderAdapter by lazy { SalesOrderAdapter() }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        tvAccountBalanceNum.text = "￥166,500.00"
        tvExplain.text = "(冻结金额：0 )"
        tvTodayIncomeNum.text = "6666"
        tvTodaySalesNum.text = "88899"
        tvTodayOrderNum.text = "4455"
        tvMonthIncomeNum.text = "6666"
        tvMonthSalesNum.text = "88899"
        tvMonthOrderNum.text = "4455"
        with(rvOrder) {
            adapter = salesOrderAdapter
        }
    }

    override fun initData() {
        salesOrderAdapter.setNewInstance(mutableListOf("", "", "", "", ""))
    }
}