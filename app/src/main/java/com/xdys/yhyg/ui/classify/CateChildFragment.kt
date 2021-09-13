package com.xdys.yhyg.ui.classify

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.hjq.toast.ToastUtils
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.config.Constant
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.classify.BannerImgAdapter
import com.xdys.yhyg.adapte.classify.CateItemAdapter
import com.xdys.yhyg.databinding.FragmentCateChildBinding
import com.xdys.yhyg.entity.classify.CategoriesBanner
import com.xdys.yhyg.vm.MineViewModel

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


    private val cateItemAdapter by lazy { CateItemAdapter() }
    var tableList = arrayOf("推荐分类", "热门选购", "裙装", "休闲零食", "医药", "个人洗护", "手机家电·")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvCate) {
            adapter = cateItemAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    var layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    var positions = layoutManager.findFirstVisibleItemPosition()
                    tabLayout.setScrollPosition(positions, 0f, true)
                }
            })
        }
        bannerView.setLifecycleRegistry(lifecycle).setAdapter(BannerImgAdapter()).create()
        with(tabLayout) {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position?.let { rvCate.scrollToPosition(it) }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.customView = null
                }

                override fun onTabReselected(tab: TabLayout.Tab?) = Unit
            })
            for (index in 0 until tableList.size) {
                addTab(tabLayout.newTab().setText(tableList[index]))
            }
        }
    }

    override fun initData() {
        binding.bannerView.refreshData(
            mutableListOf(
                CategoriesBanner(
                    "0",
                    "https://img1.baidu.com/it/u=1825851994,4163570429&fm=253&fmt=auto&app=120&f=JPEG?w=934&h=500"
                ),
                CategoriesBanner(
                    "1",
                    "https://img2.baidu.com/it/u=3495436499,1211422207&fm=26&fmt=auto&gp=0.jpg"
                ),
                CategoriesBanner(
                    "2",
                    "https://img0.baidu.com/it/u=251614576,2693916083&fm=26&fmt=auto&gp=0.jpg"
                ),
                CategoriesBanner(
                    "3",
                    "https://img0.baidu.com/it/u=2027992389,3130985476&fm=26&fmt=auto&gp=0.jpg"
                )

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