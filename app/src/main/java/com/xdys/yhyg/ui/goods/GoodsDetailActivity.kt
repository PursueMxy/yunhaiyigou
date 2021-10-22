package com.xdys.yhyg.ui.goods

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.databinding.ActivityGoodsDetailBinding
import com.xdys.yhyg.entity.goods.GenerateOrdersEntity
import com.xdys.yhyg.entity.goods.GoodsDetailEntity
import com.xdys.yhyg.entity.goods.shopId
import com.xdys.yhyg.entity.goods.skuEntity
import com.xdys.yhyg.popup.ProductSpecPopupWindow
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
        tvBuyNow.setOnClickListener {
            productSpecPopupWindow.showPopupWindow()
        }
    }

    override fun initData() {
//        intent.getStringExtra(Constant.Key.EXTRA_ID)?.let { viewModel.goodsDetail(it) }
    }


    private val productSpecPopupWindow: ProductSpecPopupWindow by lazy {
        val id: String = intent.getStringExtra(Constant.Key.EXTRA_ID).toString()
        val goodsDetail = viewModel.goodsDetailLiveData.value
        val skuId: String = goodsDetail?.skus!![0]?.spuId.toString()
        ProductSpecPopupWindow(this, "") { selectedSpec, selectedNumber, type ->
            val orderGoods = GenerateOrdersEntity(
                "2", "1", "1",
                "app", "", "", "", "", "",
                "测试数据", mutableListOf(
                    shopId(goodsDetail?.id, skuId, "5")
                )
            )
            viewModel.savaGoods(orderGoods)
        }
    }
}