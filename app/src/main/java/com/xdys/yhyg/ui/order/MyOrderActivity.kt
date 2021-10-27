package com.xdys.yhyg.ui.order

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
import com.xdys.library.config.Constant
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.ActivityMyOrderBinding
import com.xdys.yhyg.vm.OrderViewModel

class MyOrderActivity : ViewModelActivity<OrderViewModel, ActivityMyOrderBinding>() {

    override fun createBinding() = ActivityMyOrderBinding.inflate(layoutInflater)

    override val viewModel: OrderViewModel by viewModels()

    var tableList = arrayOf("全部", "待付款", "待发货", "待收货", "待评价", "已完成")

    companion object {
        fun start(context: Context, index: Int = 0) {
            val intent = Intent(context, MyOrderActivity::class.java)
                .putExtra(Constant.Key.EXTRA_ID, index)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?): Unit = with(binding) {
        with(tabLayout) {
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
            adapter = object : FragmentStateAdapter(this@MyOrderActivity) {
                override fun getItemCount(): Int {
                    return tableList.size
                }

                override fun createFragment(position: Int): Fragment {
                    return OrderFragment.newInstance(position)
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
        intent.getIntExtra(Constant.Key.EXTRA_ID, 0)?.let {
            pager.currentItem = it
        }
    }
}