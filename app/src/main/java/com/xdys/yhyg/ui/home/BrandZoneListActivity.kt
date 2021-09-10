package com.xdys.yhyg.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.to.aboomy.pager2banner.IndicatorView
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.home.ImageAdapter
import com.xdys.yhyg.databinding.ActivityBrandZoneBinding
import com.xdys.yhyg.databinding.ActivityBrandZoneListBinding
import com.xdys.yhyg.vm.HomeViewModel

class BrandZoneListActivity : ViewModelActivity<HomeViewModel, ActivityBrandZoneListBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, BrandZoneListActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val mAdapter by lazy { ImageAdapter() }

    var tableList = arrayOf("今日上薪", "母婴童装", "家居日用", "3C数码")

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        titleBar.titleContent = "品牌专区"
        with(tabLayout) {
            setSelectedTabIndicator(R.drawable.indicator_tab_goods)
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab == null) return
                    tab.customView =
                        layoutInflater.inflate(
                            R.layout.item_tab_category, null
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
            adapter = object : FragmentStateAdapter(this@BrandZoneListActivity) {
                override fun getItemCount(): Int {
                    return tableList.size
                }

                override fun createFragment(position: Int): Fragment {
                    return BrandZoneFragment.newInstance(position)
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

    }


    override fun createBinding() = ActivityBrandZoneListBinding.inflate(layoutInflater)

    override val viewModel: HomeViewModel by viewModels()
}