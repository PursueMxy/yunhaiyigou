package com.xdys.yhyg.ui.mall

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityCheckoutCounterBinding
import com.xdys.yhyg.vm.MallViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.R

class CheckoutCounterActivity : ViewModelActivity<MallViewModel, ActivityCheckoutCounterBinding>() {
    override fun createBinding() = ActivityCheckoutCounterBinding.inflate(layoutInflater)

    override val viewModel: MallViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CheckoutCounterActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?): Unit = with(binding) {
        ivIntegral.setOnClickListener { it.isSelected = !it.isSelected }
        ivLogo.loadRoundCornerImage(R.mipmap.mall_logo)
        clWeChatPay.setOnClickListener {
            clWeChatPay.isSelected = true
            clAlipay.isSelected = false
            clBalance.isSelected = false
        }
        clAlipay.setOnClickListener {
            clWeChatPay.isSelected = false
            clAlipay.isSelected = true
            clBalance.isSelected = false
        }
        clBalance.setOnClickListener {
            clWeChatPay.isSelected = false
            clAlipay.isSelected = false
            clBalance.isSelected = true
        }
        tvIntegralNum.text = "共2490积分，可用于抵扣"
        tvShopName.text = "上官糖炒栗子·四果汤(塔埔店)"
        tvActualPayment.text = "¥588"
        tvConfirmPayment.setOnClickListener {
            PaymentStatusActivity.start(this@CheckoutCounterActivity)
        }
    }
}