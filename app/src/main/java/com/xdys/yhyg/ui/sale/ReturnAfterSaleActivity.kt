package com.xdys.yhyg.ui.sale

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
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.ActivityReturnAfterSaleBinding
import com.xdys.yhyg.vm.AfterSaleViewModel

class ReturnAfterSaleActivity :
    ViewModelActivity<AfterSaleViewModel, ActivityReturnAfterSaleBinding>() {
    override fun createBinding() = ActivityReturnAfterSaleBinding.inflate(layoutInflater)

    override val viewModel: AfterSaleViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ReturnAfterSaleActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        var tableList = arrayOf("售后申请", "处理中", "申请记录")
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
            adapter = object : FragmentStateAdapter(this@ReturnAfterSaleActivity) {
                override fun getItemCount(): Int {
                    return tableList.size
                }

                override fun createFragment(position: Int): Fragment = when (position) {
                    0 -> AfterApplicationFragment.newInstance(position)
                    else -> ApplyRecordFragment.newInstance(position)
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