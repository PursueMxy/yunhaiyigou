package com.xdys.yhyg.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.node.BaseNode
import com.xdys.yhyg.adapte.cart.CartAdapter
import com.xdys.yhyg.adapte.cart.OnCartItemSelectedListener
import com.xdys.yhyg.adapte.home.HomeGoodsAdapter
import com.xdys.yhyg.databinding.FragmentCartBinding
import com.xdys.yhyg.entity.cart.CartProductEntity
import com.xdys.yhyg.entity.cart.CartShopEntity
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.extension.dp
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.R
import com.xdys.yhyg.ui.goods.GoodsDetailActivity

class CartFragment : ViewModelFragment<MineViewModel, FragmentCartBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCartBinding.inflate(inflater, container, false)

    private val goodsAdapter by lazy { HomeGoodsAdapter() }
    override val viewModel: MineViewModel by activityViewModels()
    private val cartAdapter by lazy { CartAdapter(listener) }
    private val cartList: MutableList<CartShopEntity> = mutableListOf(
        CartShopEntity("杜康古城酒业商城",
            mutableListOf(
                CartProductEntity(),
                CartProductEntity(),
                CartProductEntity(),
                CartProductEntity(),
                CartProductEntity(),
                CartProductEntity()
            )
        ), CartShopEntity("小杜赢商",
            mutableListOf(
                CartProductEntity(),
                CartProductEntity(),
                CartProductEntity(),
                CartProductEntity(),
                CartProductEntity(),
                CartProductEntity()
            )
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvCart) {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(requireContext())
            (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        }
        with(cartAdapter) {
            setEmptyView(R.layout.layout_empty_cart)
        }
        with(rvGoods) {
            adapter = goodsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(DividerItemDecoration(7.dp, 7.dp))
            goodsAdapter.setOnItemClickListener { adapter, view, position ->
                GoodsDetailActivity.start(requireContext())
            }
        }
    }

    override fun initData() {
        goodsAdapter.setNewInstance(mutableListOf("", "", "", "", ""))
        cartAdapter.setDiffNewData(cartList as? MutableList<BaseNode>)
    }

    private val listener = object : OnCartItemSelectedListener {
        // 选中状态发生变化
        override fun changed() {
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