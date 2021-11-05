package com.xdys.yhyg.ui.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.chad.library.adapter.base.entity.node.BaseNode
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant
import com.xdys.library.event.CartEvent
import com.xdys.library.event.LiveDataBus
import com.xdys.library.extension.currency
import com.xdys.library.extension.singleTop
import com.xdys.library.utils.mxyUtils
import com.xdys.yhyg.adapte.cart.ConfirmOrderAdapter
import com.xdys.yhyg.adapte.goods.OrderGoodsAdapter
import com.xdys.yhyg.databinding.ActivityConfirmOrderBinding
import com.xdys.yhyg.entity.goods.*
import com.xdys.yhyg.ui.home.MainActivity
import com.xdys.yhyg.ui.mall.PaymentStatusActivity
import com.xdys.yhyg.vm.AddressViewModel
import com.xdys.yhyg.vm.HomeViewModel
import com.xdys.yhyg.vm.OrderViewModel

class ConfirmOrderActivity : ViewModelActivity<HomeViewModel, ActivityConfirmOrderBinding>() {

    override fun createBinding() = ActivityConfirmOrderBinding.inflate(layoutInflater)

    override val viewModel: HomeViewModel by viewModels()

    val orderViewModel: OrderViewModel by viewModels()

    val addressViewModel: AddressViewModel by viewModels()

    companion object {
        fun goodsStart(context: Context, foldOrder: FoldOrder) {
            val intent = Intent(context, ConfirmOrderActivity::class.java)
                .putExtra(Constant.Key.EXTRA_DATA, foldOrder)
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
        intent.getStringExtra(Constant.Key.EXTRA_ID)?.let {
            binding.tvGoodsAmount.text = it.currency()
        }
        binding.tvPayImmediately.setOnClickListener {
            saveOrder()
        }
        tvPaymentType.text = "微信支付"

    }

    /**
     * 商品拆单
     */
    fun foldOrder(addressId: String) {
        (intent.getSerializableExtra(Constant.Key.EXTRA_DATA) as? FoldOrder)?.let {
            it.buyerAddressId = addressId
            orderViewModel.foldOrder(it)
        }
    }


    /**
     * 下单
     */
    fun saveOrder() {
        val buyShopList: MutableList<BuyShopEntity> = mutableListOf()
        val userShoppingCartIdList: MutableList<String> = mutableListOf()
        (intent.getSerializableExtra(Constant.Key.EXTRA_DATA) as? FoldOrder)?.let {
            for (cart in it.goodsList) {
                if (cart.cartId != null) {
                    userShoppingCartIdList.add(cart.cartId)
                }
            }
        }

        orderViewModel.previewOrderLiveData.value?.let {
            for (buyShop in it.buyShopList) {
                val buyGoodsList: MutableList<BuyGoodsEntity> = mutableListOf()
                for (goods in buyShop.goodsList) {
                    buyGoodsList.add(
                        BuyGoodsEntity(
                            goods.shopId, goods.spuId, goods.skuId,
                            goods.quantity, "", goods.orderType, "false", goods.deliveryMode
                        )
                    )
                }
                buyShopList.add(
                    BuyShopEntity(
                        buyShop.shopId, buyShop.deliveryMode,
                        "", buyGoodsList, "", buyShop.orderType
                    )
                )

            }
            orderViewModel.addOrder(
                GenerateOrdersEntity(
                    userShoppingCartIdList,
                    it.buyerAddressId,
                    it.paymentType,
                    buyShopList
                )
            )
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
            it.id?.let { id -> foldOrder(id) }
        }
        viewModel.savaGoodsLiveData.observe(this) {
            MainActivity.startActivity(this, true, 3)
            MyOrderActivity.start(this, 1)
        }
        orderViewModel.previewOrderLiveData.observe(this) {
            mAdapter.setNewInstance(it.buyShopList as MutableList<BaseNode>)
            binding.tvShipping.text = it.buyShopList.get(0).freight?.currency()
            binding.tvCouponDeduction.text = "￥0.00"
            binding.tvGoodsAmount.text = it.buyShopList.get(0).paymentPrice?.currency()
        }
        orderViewModel.saveOrderLiveData.observe(this) {
            LiveDataBus.post(CartEvent())
        }

    }
}