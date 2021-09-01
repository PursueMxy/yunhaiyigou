package com.xdys.yhyg.ui.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.adapte.mine.DiscountCouponAdapter
import com.xdys.yhyg.databinding.ActivityDiscountCouponBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class DiscountCouponActivity : ViewModelActivity<MineViewModel, ActivityDiscountCouponBinding>() {
    override fun createBinding() = ActivityDiscountCouponBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, DiscountCouponActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val mAdapter by lazy { DiscountCouponAdapter() }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvCoupons) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf("","",""))
    }
}