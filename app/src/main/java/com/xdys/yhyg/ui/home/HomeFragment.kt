package com.xdys.yhyg.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.home.ProductTypeAdapter
import com.xdys.yhyg.databinding.FragmentHomeBinding
import com.xdys.yhyg.entity.home.HomeClassifyBean
import com.xdys.yhyg.vm.HomeViewModel

class HomeFragment : ViewModelFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override val viewModel: HomeViewModel by activityViewModels()


    private val productTypeAdapter by lazy { ProductTypeAdapter() }



    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        clSearch.setOnClickListener { SearchActivity.start(requireContext()) }
        titleBar.setOnRightClickListener {
            drawerLayout.openDrawer(Gravity.END)
        }
        with(rvProductType) {
            adapter = productTypeAdapter
        }
        refreshLayout.setOnRefreshListener { initData() }

        tvTitle.text = "女装"

    }

    override fun initData() {
        viewModel.goodsPage()
        productTypeAdapter.setDiffNewData(mutableListOf())
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.homeLiveData.observe(this) {
            binding.refreshLayout.finishRefresh()
            binding.tabLayout.clearOnTabSelectedListeners()
            initViewPager(it.goodsCategoryListFirst)
        }
    }

    private fun initViewPager(list: MutableList<HomeClassifyBean>) {
        with(binding) {
            with(tabLayout) {
                setSelectedTabIndicator(R.drawable.indicator_tab)
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        if (tab == null) return
                        tab.customView =
                            layoutInflater.inflate(
                                R.layout.item_tab_selected, null
                            ).apply {
                                findViewById<TextView>(R.id.tvTitle).text = list[tab.position].name
                            }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                        tab?.customView = null
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) = Unit
                })
            }
            with(pager) {
                adapter = object : FragmentStateAdapter(requireActivity()) {
                    override fun getItemCount(): Int {
                        return list.size
                    }

                    override fun createFragment(position: Int): Fragment = when (position) {
                        0 -> RecommendFragment.newInstance(position)
                        else -> OtherFragment.newInstance(list[position].id!!)
                    }


                }
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                    }
                })
            }
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                tab.text = list[position].name
            }.attach()
        }
    }
}