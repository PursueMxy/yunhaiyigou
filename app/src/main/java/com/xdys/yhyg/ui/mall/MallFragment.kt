package com.xdys.yhyg.ui.mall

import android.os.Bundle
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
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.mall.MallCateAdapter
import com.xdys.yhyg.databinding.FragmentMallBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class MallFragment : ViewModelFragment<MineViewModel, FragmentMallBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMallBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    private val mAdapter by lazy { MallCateAdapter() }

    var tableList = arrayOf("附近", "水果", "鲜花蛋糕", "休闲零食", "医药", "个人洗护", "手机家电·")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvLocation.text = "厦门市观音山营运中心5号"
        with(viewPagerItem) {
            adapter = mAdapter
        }
        with(tabLayout) {
            setSelectedTabIndicator(R.drawable.indicator_tab)
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab == null) return
                    tab.customView =
                        layoutInflater.inflate(
                            R.layout.item_tab_mall, null
                        ).apply {
                            findViewById<TextView>(R.id.tvTitle).text = tableList[tab.position]
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
                    return tableList.size
                }

                override fun createFragment(position: Int): Fragment {
                    return MallSecondaryFragment.newInstance(position)
                }


            }
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = tableList[position]
        }.attach()
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf("实现效果", "等级复用", "牛逼哄哄"))
    }
}