package com.xdys.yhyg.ui.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.adapte.cart.ConfirmOrderAdapter
import com.xdys.yhyg.databinding.ActivityConfirmOrderBinding
import com.xdys.yhyg.entity.cart.CartProductEntity
import com.xdys.yhyg.entity.cart.CartShopEntity
import com.xdys.yhyg.entity.goods.GoodsDetailEntity
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
import com.xdys.yhyg.vm.CartViewModel

class ConfirmOrderActivity : ViewModelActivity<CartViewModel, ActivityConfirmOrderBinding>() {

    override fun createBinding() = ActivityConfirmOrderBinding.inflate(layoutInflater)

    override val viewModel: CartViewModel by viewModels()

    companion object {
        fun goodsStart(context: Context, cartShop: CartShopEntity) {
            val intent = Intent(context, ConfirmOrderActivity::class.java)
                .putExtra(Constant.Key.EXTRA_DATA, cartShop)
                .singleTop()
            context.startActivity(intent)
        }

        fun start(context: Context) {
            val intent = Intent(context, ConfirmOrderActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val mAdapter by lazy { ConfirmOrderAdapter() }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        var goodsDetail = intent.getSerializableExtra(Constant.Key.EXTRA_DATA) as? CartShopEntity
        with(rvGoods)
        {
            adapter = mAdapter
        }

    }


    override fun initData() {
        mAdapter.setNewInstance(
            mutableListOf(
//                CartShopEntity(
//                    "",
//                    "dd",
//                    "dd",
//                    mutableListOf(CartProductEntity(), CartProductEntity(), CartProductEntity())
//                )
            )
        )
    }
}