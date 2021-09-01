package com.xdys.yhyg.ui.classify

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.to.aboomy.pager2banner.Banner
import com.to.aboomy.pager2banner.IndicatorView
import com.xdys.yhyg.adapte.classify.CateItemAdapter
import com.xdys.yhyg.adapte.home.ImageAdapter
import com.xdys.yhyg.databinding.FragmentCateChildBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.config.Constant
import com.xdys.yhyg.R

class CateChildFragment : ViewModelFragment<MineViewModel, FragmentCateChildBinding>() {
    companion object {
        fun brandInstance(): CateChildFragment = CateChildFragment().apply {
            arguments = bundleOf(Constant.Key.EXTRA_ID to true)
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCateChildBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    private val mAdapter by lazy { ImageAdapter() }

    private val cateItemAdapter by lazy { CateItemAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        var layoutBanner = layoutInflater.inflate(R.layout.cate_banner, null, false)
        cateItemAdapter.addHeaderView(layoutBanner)
        var indicator = IndicatorView(requireContext())
            .setIndicatorColor(Color.DKGRAY)
            .setIndicatorSelectorColor(Color.WHITE)
        with(layoutBanner.findViewById<Banner>(R.id.banner)) {
            setIndicator(indicator)
            setAdapter(mAdapter)
        }
        with(rvCate) {
            adapter = cateItemAdapter
            layoutManager = LinearLayoutManager(requireContext())
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
        cateItemAdapter.setNewInstance(
            mutableListOf(
                "常用分类",
                "热门分类",
                "热卖精选",
                "数码家电",
                "汽车用品",
                "零食专场"
            )
        )
    }

    override fun initObserver() {
        super.initObserver()
    }
}