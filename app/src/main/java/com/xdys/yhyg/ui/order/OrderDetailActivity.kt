package com.xdys.yhyg.ui.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.xdys.yhyg.adapte.order.OrderProductAdapter
import com.xdys.yhyg.databinding.ActivityOrderDetailBinding
import com.xdys.yhyg.vm.OrderViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant.Key.EXTRA_DATA
import com.xdys.library.extension.singleTop
import com.xdys.library.widget.SpanView
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.order.OrderDetail
import java.text.DecimalFormat
import java.time.Instant
import kotlin.math.log

class OrderDetailActivity : ViewModelActivity<OrderViewModel, ActivityOrderDetailBinding>() {
    override fun createBinding() = ActivityOrderDetailBinding.inflate(layoutInflater)
    override val viewModel: OrderViewModel by viewModels()

    companion object {
        fun start(context: Context, orderId: String) {
            val intent = Intent(context, OrderDetailActivity::class.java)
                .putExtra(EXTRA_DATA, orderId)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val format = DecimalFormat("00")
    private val mAdapter by lazy { OrderProductAdapter() }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvGoods) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        intent.getStringExtra(EXTRA_DATA)?.let {
            viewModel.orderDetail(it)
            viewModel.logistics(it)
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.orderDetailLiveData.observe(this) {
            mAdapter.setNewInstance(it.goods?.data)
            fillUI(it)
            fillCountdown()
            viewModel.countdown(100000)
        }
        viewModel.addressLiveData.observe(this) {
            binding.tvRecipient.text = it.Name
            binding.tvMobile.text = it.telNum
            binding.tvAddress.text = "地址: ${it.address}"
        }

        viewModel.countdownLiveData.observe(this, {
            fillCountdown()
        })
    }

    fun fillUI(orderDetail: OrderDetail) {
        with(binding) {
            tvOrderStatus.text = "等待付款"
            tvOrderTime.text = "订单编号：${orderDetail.order_on}"
            tvOrderAmount.text = orderDetail.goods_amount
        }
    }

    fun fillCountdown() {
        viewModel.orderDetailLiveData.value?.valid_time?.let {

            var needCountdown =
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Instant.now().epochSecond
                } else {
                    System.currentTimeMillis() / 1000
                }
            if (it.toLong() > needCountdown) {
                val hour = format.format((it.toLong()?.minus(needCountdown))?.div((60 * 60)))
                val min = format.format((it?.toLong().minus(needCountdown))?.rem(60 * 60)?.div(60))
                val sec = format.format((it?.toLong().minus(needCountdown))?.rem(60))
                val str = " $hour : $min : $sec "
                binding.tvTime.text = "剩余  $str  自动关闭订单"
            } else {
                binding.tvTime.text = "订单已过期"
            }
        }
    }

}