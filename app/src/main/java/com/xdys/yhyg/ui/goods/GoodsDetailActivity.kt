package com.xdys.yhyg.ui.goods

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.hjq.toast.ToastUtils
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant
import com.xdys.library.extension.context
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.ActivityGoodsDetailBinding
import com.xdys.yhyg.entity.cart.*
import com.xdys.yhyg.entity.goods.*
import com.xdys.yhyg.popup.ProductSpecPopupWindow
import com.xdys.yhyg.ui.order.ConfirmOrderActivity
import com.xdys.yhyg.vm.CartViewModel
import com.xdys.yhyg.vm.HomeViewModel

class GoodsDetailActivity : ViewModelActivity<HomeViewModel, ActivityGoodsDetailBinding>() {

    companion object {
        fun start(context: Context, id: String, type: Int = 0, endTime: Int = 0) {
            val intent = Intent(context, GoodsDetailActivity::class.java)
                .putExtra(Constant.Key.EXTRA_ID, id)
                .putExtra(Constant.Key.EXTRA_INDEX, type)
                .putExtra(Constant.Key.EXTRA_DATA, endTime)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun createBinding(): ActivityGoodsDetailBinding =
        ActivityGoodsDetailBinding.inflate(layoutInflater)

    override val viewModel: HomeViewModel by viewModels()

    val cartModel: CartViewModel by viewModels()

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.goodsDetailContainer) as NavHostFragment).navController
    }

    override fun initUI(savedInstanceState: Bundle?): Unit = with(binding) {
        val type = intent?.getIntExtra(Constant.Key.EXTRA_INDEX, 0)
        val nav = navController.navInflater.inflate(R.navigation.nav_goods_detail).apply {
            startDestination = when (type) {
                1 -> R.id.goodsDetailSpikeFragment
                else -> R.id.goodsDetailFragment
            }
        }
        navController.graph = nav
        tvAddCart.setOnClickListener {
            viewModel.goodsSkuLiveData.value?.let { it1 ->
                productSpecPopupWindow.setData(it1, 0).showPopupWindow()
            }
        }
        tvBuyNow.setOnClickListener {
            viewModel.goodsSkuLiveData.value?.let { it1 ->
                productSpecPopupWindow.setData(it1, 1).showPopupWindow()
            }
        }
    }

    override fun initData() {
//        intent.getStringExtra(Constant.Key.EXTRA_ID)?.let { viewModel.goodsDetail(it) }
    }


    private val productSpecPopupWindow: ProductSpecPopupWindow by lazy {
        val id: String = intent.getStringExtra(Constant.Key.EXTRA_ID).toString()
        val goodsDetail = viewModel.goodsDetailLiveData.value
        val goodsSku = viewModel.goodsSkuLiveData.value
        ProductSpecPopupWindow(this, SpecItem()) { selectedSpec, selectedNumber, type ->
            if (type == 1) {
                var spuId = goodsSku?.get(0)?.spuId
                ConfirmOrderActivity.goodsStart(
                    this, ConfirmOrderEntity(
                        "小杜营商", goodsDetail?.shopId.toString(),
                        mutableListOf(
                            OrderGoods(
                                id,
                                goodsDetail?.name,
                                spuId.toString(),
                                selectedSpec?.id,
                                selectedSpec?.value,
                                selectedNumber.toLong(),
                                goodsDetail?.priceUp,
                                goodsDetail?.picUrls?.get(0)
                            )
                        )
                    )
                )
            } else {
                selectedSpec?.let { spec ->
                    goodsSku?.get(0)?.let {
                        cartModel.addCart(
                            it.spuId,
                            spec.id,
                            selectedNumber.toString(),
                            spec.value,
                            "566",
                            "",
                            ""
                        )
                    }
                }
            }
        }
    }
}