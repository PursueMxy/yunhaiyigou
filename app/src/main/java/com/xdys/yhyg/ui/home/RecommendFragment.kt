package com.xdys.yhyg.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.to.aboomy.pager2banner.IndicatorView
import com.xdys.yhyg.adapte.home.BrandMerchantAdapter
import com.xdys.yhyg.adapte.home.HomeCateFirstAdapter
import com.xdys.yhyg.adapte.home.HomeGoodsAdapter
import com.xdys.yhyg.adapte.home.ImageAdapter
import com.xdys.yhyg.databinding.FragmentRecommendBinding
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.extension.dp
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration

class RecommendFragment : ViewModelFragment<MineViewModel, FragmentRecommendBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecommendBinding = FragmentRecommendBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()
    private val mAdapter by lazy { ImageAdapter() }
    private val cateFirstAdapter by lazy { HomeCateFirstAdapter() }
    private val brandMerchatAdapter by lazy { BrandMerchantAdapter() }
    private val goodsAdapter by lazy { HomeGoodsAdapter() }

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = RecommendFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        var indicator = IndicatorView(requireContext())
            .setIndicatorColor(Color.DKGRAY)
            .setIndicatorSelectorColor(Color.WHITE)
        with(banner) {
            setIndicator(indicator)
            setAdapter(mAdapter)
        }
        with(rvCateFirst) {
            adapter = cateFirstAdapter
            layoutManager = GridLayoutManager(requireContext(), 5)
            addItemDecoration(DividerItemDecoration(12.px, 17.px))
        }
        with(rvBrandMerchant) {
            adapter = brandMerchatAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(DividerItemDecoration(19.dp, 3.px))
        }
        with(rvGoods) {
            adapter = goodsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(DividerItemDecoration(7.dp, 7.dp))
            goodsAdapter.setOnItemClickListener { adapter, view, position ->
                GoodsDetailActivity.start(requireContext())
            }
        }
        ivSeckill.setOnClickListener {
            CommodityTypeActivity.start(requireContext())
        }

    }

    override fun initData() {
        mAdapter.setNewInstance(
            mutableListOf(
                "https://img1.baidu.com/it/u=1825851994,4163570429&fm=253&fmt=auto&app=120&f=JPEG?w=934&h=500",
                "https://img2.baidu.com/it/u=3495436499,1211422207&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=251614576,2693916083&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=2027992389,3130985476&fm=26&fmt=auto&gp=0.jpg"
            )
        )
        cateFirstAdapter.setNewInstance(mutableListOf("新人专享", "领券中心", "积分兑换", "杜康名酒", "品牌专区"))
        brandMerchatAdapter.setNewInstance(mutableListOf("", "", "", "", "", ""))
        goodsAdapter.setNewInstance(mutableListOf("", "", "", "", "", ""))
    }
}