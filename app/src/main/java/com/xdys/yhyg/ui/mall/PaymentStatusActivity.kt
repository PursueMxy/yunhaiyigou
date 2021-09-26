package com.xdys.yhyg.ui.mall

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.dp
import com.xdys.library.extension.singleTop
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.adapte.home.HomeGoodsAdapter
import com.xdys.yhyg.databinding.ActivityPaymentStatusBinding
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
import com.xdys.yhyg.vm.MallViewModel

class PaymentStatusActivity : ViewModelActivity<MallViewModel, ActivityPaymentStatusBinding>() {

    override fun createBinding() = ActivityPaymentStatusBinding.inflate(layoutInflater)

    override val viewModel: MallViewModel by viewModels()

    private val goodsAdapter by lazy { HomeGoodsAdapter() }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, PaymentStatusActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvGoods) {
            adapter = goodsAdapter
            layoutManager = GridLayoutManager(this@PaymentStatusActivity, 2)
            addItemDecoration(DividerItemDecoration(7.dp, 7.dp))
            goodsAdapter.setOnItemClickListener { adapter, view, position ->
                GoodsDetailActivity.start(this@PaymentStatusActivity)
            }
        }
    }

    override fun initData() {
        goodsAdapter.setDiffNewData(mutableListOf())
    }
}