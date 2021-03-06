package com.xdys.yhyg.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.to.aboomy.pager2banner.IndicatorView
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.config.Constant
import com.xdys.library.extension.dp
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.home.FlashGoodsAdapter
import com.xdys.yhyg.adapte.home.HomeCateFirstAdapter
import com.xdys.yhyg.adapte.home.HomeGoodsAdapter
import com.xdys.yhyg.adapte.home.ImageAdapter
import com.xdys.yhyg.databinding.FragmentViceHomeBinding
import com.xdys.yhyg.entity.home.ButtonList
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
import com.xdys.yhyg.ui.web.WebViewActivity
import com.xdys.yhyg.vm.HomeViewModel

class ViceHomeFragment : ViewModelFragment<HomeViewModel, FragmentViceHomeBinding>() {

    override val viewModel: HomeViewModel by activityViewModels()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentViceHomeBinding.inflate(inflater, container, false)

    private val mAdapter by lazy { ImageAdapter() }

    private val goodsAdapter by lazy { HomeGoodsAdapter() }

    private val flashGoodsAdapter by lazy { FlashGoodsAdapter() }

    private val cateFirstAdapter by lazy { HomeCateFirstAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        var indicator = IndicatorView(requireContext())
            .setIndicatorColor(Color.DKGRAY)
            .setIndicatorSelectorColor(Color.WHITE)
        with(banner) {
            setIndicator(indicator)
            adapter = mAdapter
        }
        with(rvFlashGoods) {
            adapter = flashGoodsAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        with(rvGoods) {
            adapter = goodsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(DividerItemDecoration(7.dp, 7.dp))
        }
        with(rvCateFirst) {
            adapter = cateFirstAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
            addItemDecoration(DividerItemDecoration(12.px, 17.px))
        }
        with(goodsAdapter) {
            setOnItemClickListener { _, _, position ->
                data[position].id?.let { GoodsDetailActivity.start(requireContext(), it) }
            }
        }

        with(flashGoodsAdapter) {
            setOnItemClickListener { _, _, position ->
                data[position].spuId?.let { GoodsDetailActivity.start(requireContext(), it, 1) }
            }
        }
        with(cateFirstAdapter) {
            setOnItemClickListener { _, view, position ->
                WebViewActivity.start(
                    requireContext(),
                    "${Constant.webUrl}/rich/${data[position].id}",
                    data[position].text
                )
            }
        }
        clSearch.setOnClickListener {
            SearchActivity.start(requireContext())
        }
        refreshLayout.setOnRefreshListener {
            initData()
        }
        tvSeeMore.setOnClickListener {
            FlashSaleActivity.start(requireContext())
        }
    }

    override fun initData() {
        viewModel.goodsPage()
//        viewModel.favGoods()
        viewModel.seckillHall()
        viewModel.goodsSpuPage()
        cateFirstAdapter.setNewInstance(
            mutableListOf(
                ButtonList("1", R.mipmap.authentic_guarantee, "????????????"),
                ButtonList("4", R.mipmap.traceability_platform, "????????????"),
                ButtonList("2", R.mipmap.origin_straight, "????????????"),
                ButtonList("3", R.mipmap.home_about, "????????????")
            )
        )
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.homeLiveData.observe(this) {
            binding.refreshLayout.finishRefresh()
            mAdapter.setNewInstance(it.carouselList)
            val tvBannerList: MutableList<String> = mutableListOf()
            for (banner in it.noticeList) {
                banner.name?.let { it1 -> tvBannerList.add(it1) }
            }
            binding.tbvContent.setDatas(tvBannerList)
        }
        viewModel.favGoodsLiveData.observe(this) {
            goodsAdapter.setDiffNewData(it.records)
        }
        viewModel.seckillHallLiveData.observe(this) {
            flashGoodsAdapter.setDiffNewData(it.records.get(0).seckillGoods)
        }
        viewModel.homeGoodsLiveData.observe(this){
            goodsAdapter.setDiffNewData(it.records)
        }
    }


    override fun onResume() {
        super.onResume()
        binding.tbvContent.startViewAnimator()
    }

    override fun onStop() {
        super.onStop()
        binding.tbvContent.stopViewAnimator()
    }
}