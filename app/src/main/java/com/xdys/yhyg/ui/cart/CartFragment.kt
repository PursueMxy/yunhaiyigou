package com.xdys.yhyg.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.event.CartEvent
import com.xdys.library.event.LiveDataBus
import com.xdys.library.extension.dp
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.cart.CartAdapter
import com.xdys.yhyg.adapte.cart.OnCartItemSelectedListener
import com.xdys.yhyg.adapte.home.HomeGoodsAdapter
import com.xdys.yhyg.databinding.FragmentCartBinding
import com.xdys.yhyg.entity.cart.CartProductEntity
import com.xdys.yhyg.entity.cart.CartShopEntity
import com.xdys.yhyg.entity.goods.ConfirmOrderEntity
import com.xdys.yhyg.entity.goods.FoldGoods
import com.xdys.yhyg.entity.goods.FoldOrder
import com.xdys.yhyg.entity.goods.OrderGoods
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
import com.xdys.yhyg.ui.order.ConfirmOrderActivity
import com.xdys.yhyg.vm.AddressViewModel
import com.xdys.yhyg.vm.CartViewModel
import com.xdys.yhyg.vm.HomeViewModel

class CartFragment : ViewModelFragment<CartViewModel, FragmentCartBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCartBinding.inflate(inflater, container, false)

    private val goodsAdapter by lazy { HomeGoodsAdapter() }
    override val viewModel: CartViewModel by activityViewModels()
    val addressViewModel: AddressViewModel by viewModels()

    val homeViewModel: HomeViewModel by viewModels()

    private val cartAdapter: CartAdapter by lazy { CartAdapter(listener) }

    var allPrice: String = "0.00"

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        with(rvGoods) {
            layoutManager = GridLayoutManager(activity, 2).apply {
                spanSizeLookup.isSpanIndexCacheEnabled = true
            }
            adapter = goodsAdapter
            addItemDecoration(DividerItemDecoration(7.dp, 7.dp))

        }
        with(goodsAdapter) {
            setHeaderView(createHeaderView())
            headerWithEmptyEnable = true
            setOnItemClickListener { _, _, position ->
                data[position].id?.let { GoodsDetailActivity.start(requireContext(), it, 1) }
            }
        }
        tvToSettle.setOnClickListener {
            toSettle()
        }
        cbCartAll.setOnClickListener {
            cartAdapter.refreshStatusEntity(true, cbCartAll.isChecked)
        }
        tvTotalAmount.text = "合计:￥0.00"
        tvDiscount.text = "优惠减:￥0.00"
        tvEdit.setOnClickListener {
            when (tvEdit.text.toString()) {
                "编辑" -> {
                    tvEdit.text = "完成"
                    gpEdit.visibility = View.VISIBLE
                    gpToSettle.visibility = View.GONE
                }
                "完成" -> {
                    tvEdit.text = "编辑"
                    cartAdapter.editMode = true
                    gpEdit.visibility = View.GONE
                    gpToSettle.visibility = View.VISIBLE
                }
            }
        }

        btnDelete.setOnClickListener {
            var ids = cartAdapter.getCartIds()
            viewModel.deleteCart(ids)
        }
        refreshLayout.setOnRefreshListener { viewModel.cartList() }
    }

    override fun initData() {
        viewModel.cartList()
        addressViewModel.defaultAddress()
        homeViewModel.favGoods()
    }

    private fun createHeaderView(): View {
        return layoutInflater.inflate(R.layout.item_cart_top, null).apply {
            // 设置购物车商品Adapter
            with(findViewById<RecyclerView>(R.id.rvCart)) {
                layoutManager = LinearLayoutManager(context)
                adapter = cartAdapter
                (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
            }
            cartAdapter.setEmptyView(R.layout.layout_empty_cart)
        }
    }

    override fun initObserver() {
        viewModel.cartLiveData.observe(this) {
            binding.refreshLayout.finishRefresh()
            cartAdapter.setNewInstance(it.list as MutableList<BaseNode>)
        }
        viewModel.cartDeleteLivaData.observe(this) {
            viewModel.cartList()
        }
        viewModel.cartUpdateLiveData.observe(this) {
//           viewModel.cartList()
        }
        addressViewModel.defaultAddressLivaData.observe(this) {
            binding.tvAddress.text = "配送至:${it.detailedAddress}"
        }
        homeViewModel.favGoodsLiveData.observe(this) {
            goodsAdapter.setNewInstance(it.records)
        }
        LiveDataBus.toObserve(CartEvent::class.java).observe(this) {
            viewModel.cartList()
        }
    }

    /**
     * 结算
     */
    private fun toSettle() {
        val goodsList: MutableList<FoldGoods> = mutableListOf()
        val shop = viewModel.cartLiveData.value?.list?.get(0)
        for (product in cartAdapter.data) (product as? CartProductEntity)?.let {
            if (product.selected) {
                goodsList.add(
                    FoldGoods(
                        shop?.shopId,
                        product.spuId, product.skuId, product.quantity.toString(),
                        "", "0", "1", product.userShoppingCartId

                    )
                )
            }
        }
        if (goodsList.size > 0) {
            ConfirmOrderActivity.goodsStart(
                requireContext(), FoldOrder("", goodsList)
            )
        }
    }

    private val listener = object : OnCartItemSelectedListener {

        // 选中状态发生变化
        override fun changed() {
        }

        override fun changeProduct(cartProduct: CartProductEntity) {
            cartAdapter.refreshAllCart(cartProduct.goodsSku.shopId.toString())
        }

        override fun changedShop(cartShop: CartShopEntity) {
            cartAdapter.refreshShopEntity(cartShop)

        }

        // 数量变化
        override fun numberChange(
            cartProduct: CartProductEntity, type: Int,
            originNumber: Int
        ) {
            val map = hashMapOf(
                "id" to cartProduct.userShoppingCartId,
                "spuId" to cartProduct.spuId,
                "skuId" to cartProduct.skuId,
                "quantity" to type.toString(),
                "spuName" to cartProduct.goodsSpu?.name,
                "addPrice" to cartProduct.goodsSpu?.priceDown,
                "specInfo" to "",
                "picUrl" to cartProduct.goodsSpu?.picUrls
            )
            viewModel.updateCart(map)
            cartAdapter.notifyDataSetChanged()
            cartAdapter.sumPrice()
        }


        // 删除购物车item
        override fun itemDelete(uiPosition: Int) {
        }

        override fun itemClick(uiPosition: Int, product: CartProductEntity) {
        }

        override fun allTolPrice(totalPrice: String) {
            binding.tvTotalAmount.text = "总计：${totalPrice}"
            allPrice = totalPrice
        }
    }


}