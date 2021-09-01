package com.xdys.yhyg.ui.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.adapte.mine.ActivitiesAdapter
import com.xdys.yhyg.databinding.ActivityMallActivitiesBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class MallActivitiesActivity : ViewModelActivity<MineViewModel, ActivityMallActivitiesBinding>() {
    override fun createBinding() = ActivityMallActivitiesBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    private val activitiesAdapter by lazy {
        ActivitiesAdapter()
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MallActivitiesActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvActivities) {
            adapter = activitiesAdapter
        }
    }

    override fun initData() {
        activitiesAdapter.setNewInstance(mutableListOf("", "", ""))
    }
}