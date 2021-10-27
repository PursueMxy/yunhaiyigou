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
import com.xdys.yhyg.adapte.goods.OrderGoodsAdapter
import com.xdys.yhyg.databinding.ActivityConfirmOrderBinding
import com.xdys.yhyg.entity.cart.CartEntity
import com.xdys.yhyg.entity.cart.CartProductEntity
import com.xdys.yhyg.entity.cart.CartShopEntity
import com.xdys.yhyg.entity.goods.*
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
import com.xdys.yhyg.vm.AddressViewModel
import com.xdys.yhyg.vm.CartViewModel
import com.xdys.yhyg.vm.HomeViewModel

class ConfirmOrderActivity : ViewModelActivity<HomeViewModel, ActivityConfirmOrderBinding>() {

    override fun createBinding() = ActivityConfirmOrderBinding.inflate(layoutInflater)

    override val viewModel: HomeViewModel by viewModels()

    val addressViewModel: AddressViewModel by viewModels()

    companion object {
        fun goodsStart(context: Context, cartShop: ConfirmOrderEntity) {
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

    private val mAdapter by lazy { OrderGoodsAdapter() }

    override fun initUI(savedInstanceState: Bundle?): Unit = with(binding) {
        with(rvGoods) {
            adapter = mAdapter
        }
        (intent.getSerializableExtra(Constant.Key.EXTRA_DATA) as? ConfirmOrderEntity)?.let {
            binding.tvShopName.text = it.shopName + it.shopId
            mAdapter.setNewInstance(it.goodsList)
        }
        binding.tvPayImmediately.setOnClickListener {
            saveOrder()
        }

    }

    /**
     * 下单
     */
    fun saveOrder() {
        val shopList: MutableList<GoodsList> = mutableListOf()
        val goodsList: MutableList<GoodsOrder> = mutableListOf()
        (intent.getSerializableExtra(Constant.Key.EXTRA_DATA) as? ConfirmOrderEntity)?.let {
            for (goods in it.goodsList) {
                goodsList.add(GoodsOrder(goods.goodsId, goods.skuId, goods.quantity.toString()))
            }
            shopList.add(GoodsList(it.shopId, goodsList))
            val orderGoods = GenerateOrdersEntity(
                "2", "1", "1",
                "app", "0", "0", "0", "0", "0",
                "测试数据", ShopList(shopList), addressViewModel.defaultAddressLivaData.value?.id
            )
            viewModel.savaGoods(orderGoods)
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
            binding.tvConsignee.text = it.consigneeName
            binding.tvMobile.text = it.phone
        }
        viewModel.savaGoodsLiveData.observe(this) {
            MyOrderActivity.start(this)
        }
    }
}