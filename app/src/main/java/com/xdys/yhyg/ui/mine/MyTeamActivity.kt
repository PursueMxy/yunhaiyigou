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
import com.xdys.library.extension.loadCircleImage
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.ActivityMyTeamBinding
import com.xdys.yhyg.vm.MineViewModel

class MyTeamActivity : ViewModelActivity<MineViewModel, ActivityMyTeamBinding>() {
    override fun createBinding() = ActivityMyTeamBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MyTeamActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        var tableList = resources.getStringArray(R.array.my_team_page)
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
            adapter = object : FragmentStateAdapter(this@MyTeamActivity) {
                override fun getItemCount(): Int {
                    return tableList.size
                }

                override fun createFragment(position: Int): Fragment = when (position) {
                    1 -> ProfitSharingFragment.newInstance(position)
                    else -> MyMemberFragment.newInstance(position)

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
        tvTeamNumber.text = "团队：18人"
        ivAvatar.loadCircleImage(R.mipmap.schoolgirl)
        tvName.text = "醉酒三千杯"
        tvPersonalPerformance.text = "888"
        tvProfitSharingIncome.text = "955"
        tvTeamPerformance.text = "9865"
    }
}