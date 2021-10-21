package com.xdys.yhyg.ui.classify

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
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.ActivitySingleCategoryBinding
import com.xdys.yhyg.vm.CateViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant
import com.xdys.library.config.Constant.Key.EXTRA_ID
import com.xdys.library.config.Constant.Key.EXTRA_TITLE
import com.xdys.library.extension.singleTop

class SingleCategoryActivity : ViewModelActivity<CateViewModel, ActivitySingleCategoryBinding>() {

    companion object {
        fun start(context: Context,title:String,id:String) {
            val intent = Intent(context, SingleCategoryActivity::class.java)
                .putExtra(EXTRA_ID,id)
                .putExtra(EXTRA_TITLE,title)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun createBinding() = ActivitySingleCategoryBinding.inflate(layoutInflater)

    override val viewModel: CateViewModel by viewModels()

    var tableList = arrayOf("全部", "新品", "超值", "商城")

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        titleBar.titleContent = "连衣裙"
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
            adapter = object : FragmentStateAdapter(this@SingleCategoryActivity) {
                override fun getItemCount(): Int {
                    return tableList.size
                }

                override fun createFragment(position: Int): Fragment = when (position) {
                    3 -> ShopFragment.newInstance(position)
                    else -> CategoryFragment.newInstance(position)
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