package com.xdys.yhyg.ui.goods

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityGoodsDetailBinding
import com.xdys.yhyg.popup.ProductSpecPopupWindow
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.*

class GoodsDetailActivity : ViewModelActivity<MineViewModel, ActivityGoodsDetailBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, GoodsDetailActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun createBinding(): ActivityGoodsDetailBinding =
        ActivityGoodsDetailBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()



    override fun initUI(savedInstanceState: Bundle?): Unit = with(binding) {
        tvAddCart.setOnClickListener { productSpecPopupWindow.showPopupWindow() }
        tvBuyNow.setOnClickListener { productSpecPopupWindow.showPopupWindow() }
    }




    private val productSpecPopupWindow: ProductSpecPopupWindow by lazy {
        ProductSpecPopupWindow(this, "") { selectedSpec, selectedNumber, type ->
        }
    }
}