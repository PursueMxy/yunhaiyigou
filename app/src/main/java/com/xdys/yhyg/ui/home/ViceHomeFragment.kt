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
import com.xdys.library.extension.dp
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.adapte.home.FlashGoodsAdapter
import com.xdys.yhyg.adapte.home.HomeCateFirstAdapter
import com.xdys.yhyg.adapte.home.HomeGoodsAdapter
import com.xdys.yhyg.adapte.home.ImageAdapter
import com.xdys.yhyg.databinding.FragmentViceHomeBinding
import com.xdys.yhyg.entity.home.ButtonList
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
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
                data[position].spuId?.let { GoodsDetailActivity.start(requireContext(), it) }
            }
        }
        clSearch.setOnClickListener {
            SearchActivity.start(requireContext())
        }
        refreshLayout.setOnRefreshListener {
            initData()
        }
    }

    override fun initData() {
        viewModel.goodsPage()
        viewModel.favGoods()
        viewModel.seckillHall()
        cateFirstAdapter.setNewInstance(
            mutableListOf(
                ButtonList("0", "", "正品保证"),
                ButtonList("0", "", "溯源平台"),
                ButtonList("0", "", "产地直供"),
                ButtonList("0", "", "关于我们")
            )
        )
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.homeLiveData.observe(this) {
            binding.refreshLayout.finishRefresh()
            mAdapter.setNewInstance(it.carouselList)
        }
        viewModel.favGoodsLiveData.observe(this) {
            goodsAdapter.setDiffNewData(it.records)
        }
        viewModel.seckillHallLiveData.observe(this) {
            flashGoodsAdapter.setDiffNewData(it.records.get(0).seckillGoods)
        }
    }

}