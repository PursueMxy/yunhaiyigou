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
import com.xdys.yhyg.vm.HomeViewModel

class BrandZoneActivity : ViewModelActivity<HomeViewModel, ActivityBrandZoneBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, BrandZoneActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val mAdapter by lazy { ImageAdapter() }

    var tableList = arrayOf("全部", "短外套", "毛衣", "风衣", "毛呢大衣", "衬衣")

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        var indicator = IndicatorView(this@BrandZoneActivity)
            .setIndicatorColor(Color.DKGRAY)
            .setIndicatorSelectorColor(Color.WHITE)
        with(banner) {
            setIndicator(indicator)
            setAdapter(mAdapter)
        }
        titleBar.titleContent = "梵希蔓服饰品牌专场"
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
            adapter = object : FragmentStateAdapter(this@BrandZoneActivity) {
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
        ivConversion.setOnClickListener {
            if (it.isSelected) viewModel.conversion(false) else viewModel.conversion(true)
            it.isSelected = !it.isSelected
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
    }


    override fun createBinding() = ActivityBrandZoneBinding.inflate(layoutInflater)

    override val viewModel: HomeViewModel by viewModels()
}