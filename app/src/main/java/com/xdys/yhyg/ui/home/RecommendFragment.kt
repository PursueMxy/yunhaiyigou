package com.xdys.yhyg.ui.home

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.to.aboomy.pager2banner.IndicatorView
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.config.Constant
import com.xdys.library.extension.dp
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.library.widget.SpanView
import com.xdys.yhyg.R
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
    private val brandMerchantAdapter by lazy { BrandMerchantAdapter() }
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
            adapter = brandMerchantAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(DividerItemDecoration(19.dp, 3.px))
        }
        with(rvGoods) {
            adapter = goodsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(DividerItemDecoration(7.dp, 7.dp))

        }
        with(goodsAdapter) {
            setOnItemClickListener { _, _, position ->
                data[position].id?.let { GoodsDetailActivity.start(requireContext(), it) }
            }
        }
        ivSeckill.setOnClickListener {
            CommodityTypeActivity.start(requireContext())
        }
        with(brandMerchantAdapter) {
            setOnItemClickListener { _, _, position ->
                WebViewActivity.start(
                    requireContext(),
                    "${Constant.webUrl}/exclusive/id?${data[position].id}"
                )
            }
        }
        tvSeeMore.setOnClickListener {
            WebViewActivity.start(requireContext(), "${Constant.webUrl}/brand")
        }
        with(cateFirstAdapter) {
            setOnItemClickListener { _, _, position ->
                when (position) {
                    1 -> CouponCenterActivity.start(requireContext())
                }
            }
        }
    }

    override fun initData() {
        viewModel.favGoods()
        viewModel.shopFav()
    }

    fun countDown() {
        val str = " 10 : 20 : 56 "
        val builder = SpannableStringBuilder(str)
        builder.setSpan(
            SpanView(
                ContextCompat.getColor(requireContext(), R.color.RE3),
                ContextCompat.getColor(requireContext(), R.color.white)
            ),
            0, 2 + 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        builder.setSpan(
            SpanView(
                ContextCompat.getColor(requireContext(), R.color.RE3),
                ContextCompat.getColor(requireContext(), R.color.white)
            ),
            str.length - 9, str.length - 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        builder.setSpan(
            SpanView(
                ContextCompat.getColor(requireContext(), R.color.RE3),
                ContextCompat.getColor(requireContext(), R.color.white)
            ),
            str.length - 4, str.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        binding.tvUntilTime.text = builder
    }


    override fun initObserver() {
        super.initObserver()
        viewModel.homeLiveData.observe(this) {
            mAdapter.setNewInstance(it.carouselList)
//            cateFirstAdapter.setNewInstance(it.buttonList)
            countDown()
        }
        viewModel.favGoodsLiveData.observe(this) {
            goodsAdapter.setDiffNewData(it.records)
        }
        viewModel.shopFavLiveData.observe(this) {
            brandMerchantAdapter.setDiffNewData(it.records)
        }
    }
}