package com.xdys.yhyg.ui.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.chad.library.adapter.base.entity.node.BaseNode
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.adapte.cart.ConfirmOrderAdapter
import com.xdys.yhyg.databinding.ActivityConfirmOrderBinding
import com.xdys.yhyg.entity.cart.CartEntity
import com.xdys.yhyg.entity.cart.CartProductEntity
import com.xdys.yhyg.entity.cart.CartShopEntity
import com.xdys.yhyg.entity.goods.GoodsDetailEntity
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
import com.xdys.yhyg.vm.AddressViewModel
import com.xdys.yhyg.vm.CartViewModel

class ConfirmOrderActivity : ViewModelActivity<CartViewModel, ActivityConfirmOrderBinding>() {

    override fun createBinding() = ActivityConfirmOrderBinding.inflate(layoutInflater)

    override val viewModel: CartViewModel by viewModels()

    val addressViewModel: AddressViewModel by viewModels()

    companion object {
        fun goodsStart(context: Context, cartShop: CartEntity) {
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

    override fun initUI(savedInstanceState: Bundle?): Unit = with(binding) {
        with(rvGoods) {
            adapter = mAdapter
        }
        (intent.getSerializableExtra(Constant.Key.EXTRA_DATA) as? CartEntity)?.let {
            mAdapter.setNewInstance(it.list as MutableList<BaseNode>)
        }

    }


    override fun initData() {
        addressViewModel.defaultAddress()
    }

    override fun initObserver() {
        super.initObserver()
        addressViewModel.defaultAddressLivaData.observe(this) {
            binding.tvAddressDetail.text = it.detailedAddress
            binding.tvAddress.text = "${it.provinceName}${it.cityName}${it.townsName}"
            binding.tvConsignee.text =it.consigneeName
            binding.tvMobile.text = it.phone
        }
    }
}