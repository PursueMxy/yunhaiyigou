package com.xdys.yhyg.ui.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.adapte.order.OrderProductAdapter
import com.xdys.yhyg.databinding.ActivityOrderDetailBinding
import com.xdys.yhyg.vm.OrderViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.entity.order.OrderProductEntity

class OrderDetailActivity : ViewModelActivity<OrderViewModel, ActivityOrderDetailBinding>() {
    override fun createBinding() = ActivityOrderDetailBinding.inflate(layoutInflater)
    override val viewModel: OrderViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, OrderDetailActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val mAdapter by lazy { OrderProductAdapter() }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        tvOrderStatus.text = "等待付款"
        tvTime.text = "剩余  00:29:25  自动关闭订单"
        tvRecipient.text = "杜康"
        tvMobile.text = "138****9999"
        tvAddress.text = "地址:福建厦门湖里区五缘湾安岭路1008号丰润金融中心B座1001-1室"
        tvOrderTime.text = "订单编号：202122546300"
        with(rvGoods) {
            adapter = mAdapter
        }
    }

    override fun initData() {
    }
}