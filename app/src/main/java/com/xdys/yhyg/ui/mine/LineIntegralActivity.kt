package com.xdys.yhyg.ui.mine

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
import com.xdys.yhyg.databinding.ActivityLineIntegralBinding
import com.xdys.yhyg.popup.IntegralRulePopupWindow
import com.xdys.yhyg.vm.MineViewModel

class LineIntegralActivity : ViewModelActivity<MineViewModel, ActivityLineIntegralBinding>() {
    override fun createBinding() = ActivityLineIntegralBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LineIntegralActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    var tableList = arrayOf("积分明细", "兑换记录")

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
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
            adapter = object : FragmentStateAdapter(this@LineIntegralActivity) {
                override fun getItemCount(): Int {
                    return tableList.size
                }

                override fun createFragment(position: Int): Fragment {
                    return PointsDetailsFragment.newInstance(position)
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
        titleBar.setOnRightClickListener {
            popupRule.showPopupWindow()
        }
    }

    private val popupRule: IntegralRulePopupWindow by lazy {
        IntegralRulePopupWindow(this) {
        }
    }
}