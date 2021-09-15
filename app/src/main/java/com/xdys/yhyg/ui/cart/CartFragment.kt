package com.xdys.yhyg.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.extension.dp
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.cart.CartAdapter
import com.xdys.yhyg.adapte.cart.OnCartItemSelectedListener
import com.xdys.yhyg.adapte.home.HomeGoodsAdapter
import com.xdys.yhyg.databinding.FragmentCartBinding
import com.xdys.yhyg.entity.cart.CartProductEntity
import com.xdys.yhyg.entity.cart.CartShopEntity
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
import com.xdys.yhyg.ui.order.ConfirmOrderActivity
import com.xdys.yhyg.vm.CartViewModel

class CartFragment : ViewModelFragment<CartViewModel, FragmentCartBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCartBinding.inflate(inflater, container, false)

    private val goodsAdapter by lazy { HomeGoodsAdapter() }
    override val viewModel: CartViewModel by activityViewModels()
    private val cartAdapter: CartAdapter by lazy { CartAdapter(listener) }


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
                GoodsDetailActivity.start(requireContext())
            }
        }
        tvToSettle.setOnClickListener {
            ConfirmOrderActivity.start(requireContext())
        }
        cbCartAll.setOnClickListener {
            cartAdapter.refreshStatusEntity(true,cbCartAll.isChecked)
        }
    }

    override fun initData() {
        viewModel.cart()
        goodsAdapter.setNewInstance(mutableListOf("", ""))
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
            cartAdapter.setNewInstance(it.cartList as MutableList<BaseNode>)
        }
    }

    private val listener = object : OnCartItemSelectedListener {
        // 选中状态发生变化
        override fun changed() {

        }

        override fun changedShop(cartShop: CartShopEntity) {
            cartAdapter.refreshShopEntity(cartShop)
        }

        // 数量变化
        override fun numberChange(uiPosition: Int, type: Int, originNumber: Int, maxCount: Int) {

        }

        // 删除购物车item
        override fun itemDelete(uiPosition: Int) {

        }

        override fun itemClick(uiPosition: Int, product: CartProductEntity) {

        }
    }
}