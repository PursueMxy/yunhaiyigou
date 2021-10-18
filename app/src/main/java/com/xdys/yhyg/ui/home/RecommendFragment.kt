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
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.extension.dp
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.adapte.home.BrandMerchantAdapter
import com.xdys.yhyg.adapte.home.HomeCateFirstAdapter
import com.xdys.yhyg.adapte.home.HomeGoodsAdapter
import com.xdys.yhyg.adapte.home.ImageAdapter
import com.xdys.yhyg.databinding.FragmentRecommendBinding
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
import com.xdys.yhyg.ui.web.WebViewActivity
import com.xdys.yhyg.vm.HomeViewModel

class RecommendFragment : ViewModelFragment<HomeViewModel, FragmentRecommendBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecommendBinding = FragmentRecommendBinding.inflate(inflater, container, false)

    override val viewModel: HomeViewModel by activityViewModels()
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
            adapter = mAdapter
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
        with(brandMerchatAdapter) {
            setOnItemClickListener { _, _, position ->
                BrandZoneActivity.start(requireContext())
            }
        }
        with(cateFirstAdapter) {
            setOnItemClickListener { _, _, position ->
                when (position) {
                    1 -> CouponCenterActivity.start(requireContext())
                    4 -> WebViewActivity.start(requireContext())
                }
            }
        }
    }

    override fun initData() {
        cateFirstAdapter.setNewInstance(mutableListOf("新人专享", "领券中心", "积分兑换", "杜康名酒", "品牌专区"))
        brandMerchatAdapter.setDiffNewData(mutableListOf())
        goodsAdapter.setDiffNewData(mutableListOf())
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.classifyLiveData.observe(this) {
            mAdapter.setNewInstance(it.carouselList)
        }
    }
}