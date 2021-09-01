package com.xdys.yhyg.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.xdys.yhyg.databinding.ActivityCommodityTypeBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.R

class CommodityTypeActivity : ViewModelActivity<MineViewModel, ActivityCommodityTypeBinding>() {
    override fun createBinding() = ActivityCommodityTypeBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    var tableList = arrayOf("全部", "服装美妆", "食品生鲜", "家居百货", "3C电器", "酒水", "家居日用")

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CommodityTypeActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }
    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(tabLayout) {
            setSelectedTabIndicator(R.drawable.indicator_tab)
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab == null) return
                    tab.customView =
                        layoutInflater.inflate(
                            R.layout.item_tab_selected, null
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
            adapter = object : FragmentStateAdapter(this@CommodityTypeActivity) {
                override fun getItemCount(): Int {
                    return tableList.size
                }

                override fun createFragment(position: Int): Fragment {
                    return CommodityTypeFragment.newInstance(position)
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

}