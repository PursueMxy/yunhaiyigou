
package com.xdys.yhyg.ui.home

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import com.google.android.material.tabs.TabLayout
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.ActivityMainBinding
import com.xdys.yhyg.ui.cart.CartFragment
import com.xdys.yhyg.ui.classify.ClassificationFragment
import com.xdys.yhyg.ui.mall.MallFragment
import com.xdys.yhyg.ui.mine.MineFragment
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity

class MainActivity : ViewModelActivity<MineViewModel, ActivityMainBinding>() {
    override fun createBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val classificationFragment: ClassificationFragment by lazy { ClassificationFragment() }
    private val mallFragment: MallFragment by lazy { MallFragment() }
    private val cartFragment: CartFragment by lazy { CartFragment() }
    private val myFragment: MineFragment by lazy { MineFragment() }

    override fun initUI(savedInstanceState: Bundle?) {
        super.initUI(savedInstanceState)
        showFragment(0)
        with(binding) {
            with(tabLayout) {
                addTab(
                    newTab().setCustomView(
                        layoutInflater.inflate(R.layout.tab_main, null).apply {
                            findViewById<TextView>(R.id.tabMain).setCompoundDrawablesRelativeWithIntrinsicBounds(
                                0, R.drawable.tab_home, 0, 0
                            )
                            findViewById<TextView>(R.id.tabMain).text = getString(R.string.home)
                        })
                )
                addTab(
                    newTab().setCustomView(
                        layoutInflater.inflate(R.layout.tab_main, null).apply {
                            findViewById<TextView>(R.id.tabMain).setCompoundDrawablesRelativeWithIntrinsicBounds(
                                0, R.drawable.tab_classification, 0, 0
                            )
                            findViewById<TextView>(R.id.tabMain).text =
                                getString(R.string.classification)
                        })
                )
                addTab(
                    newTab().setCustomView(
                        layoutInflater.inflate(R.layout.tab_main, null).apply {
                            findViewById<TextView>(R.id.tabMain).setCompoundDrawablesRelativeWithIntrinsicBounds(
                                0, R.drawable.tab_mall, 0, 0
                            )
                            findViewById<TextView>(R.id.tabMain).text = getString(R.string.mall)
                        })
                )
                addTab(
                    newTab().setCustomView(
                        layoutInflater.inflate(R.layout.tab_main, null).apply {
                            findViewById<TextView>(R.id.tabMain).setCompoundDrawablesRelativeWithIntrinsicBounds(
                                0, R.drawable.tab_cart, 0, 0
                            )
                            findViewById<TextView>(R.id.tabMain).text = getString(R.string.cart)
                        })
                )
                addTab(
                    newTab().setCustomView(
                        layoutInflater.inflate(R.layout.tab_main, null).apply {
                            findViewById<TextView>(R.id.tabMain).setCompoundDrawablesRelativeWithIntrinsicBounds(
                                0, R.drawable.tab_my, 0, 0
                            )
                            findViewById<TextView>(R.id.tabMain).text = getString(R.string.my)
                        })
                )
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    private var prePosition = 0
                    override fun onTabReselected(tab: TabLayout.Tab?) = Unit

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                        tab?.position?.let { prePosition = it }
                    }

                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        prePosition = tab?.position ?: 0
                        showFragment(prePosition)
                    }
                })
            }
        }
    }


    private fun showFragment(index: Int) {
        if (index > 4 || index < 0) return
        val fragment = when (index) {
            1 -> classificationFragment
            2 -> mallFragment
            3 -> cartFragment
            4 -> myFragment
            else -> homeFragment
        }
        if (fragment.isAdded && fragment.isVisible) return
        supportFragmentManager.commit {
            supportFragmentManager.fragments.forEach { hide(it) }
            if (fragment.isAdded) show(fragment)
            else add(R.id.container, fragment).setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
        }

    }
}