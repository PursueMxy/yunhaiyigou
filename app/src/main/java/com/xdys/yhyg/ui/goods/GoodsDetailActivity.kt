package com.xdys.yhyg.ui.goods

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityGoodsDetailBinding
import com.xdys.yhyg.popup.ProductSpecPopupWindow
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant
import com.xdys.library.extension.*
import com.xdys.yhyg.vm.HomeViewModel

class GoodsDetailActivity : ViewModelActivity<HomeViewModel, ActivityGoodsDetailBinding>() {

    companion object {
        fun start(context: Context, id: String) {
            val intent = Intent(context, GoodsDetailActivity::class.java)
                .putExtra(Constant.Key.EXTRA_ID, id)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun createBinding(): ActivityGoodsDetailBinding =
        ActivityGoodsDetailBinding.inflate(layoutInflater)

    override val viewModel: HomeViewModel by viewModels()


    override fun initUI(savedInstanceState: Bundle?): Unit = with(binding) {
        tvAddCart.setOnClickListener { productSpecPopupWindow.showPopupWindow() }
        tvBuyNow.setOnClickListener { productSpecPopupWindow.showPopupWindow() }
    }

    override fun initData() {
        intent.getStringExtra(Constant.Key.EXTRA_ID)?.let { viewModel.goodsDetail(it) }
    }

    override fun initObserver() {
        super.initObserver()
    }


    private val productSpecPopupWindow: ProductSpecPopupWindow by lazy {
        ProductSpecPopupWindow(this, "") { selectedSpec, selectedNumber, type ->
        }
    }
}