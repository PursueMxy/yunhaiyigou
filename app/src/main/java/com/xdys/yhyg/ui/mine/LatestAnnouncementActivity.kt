package com.xdys.yhyg.ui.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.adapte.mine.AnnouncementAdapter
import com.xdys.yhyg.databinding.ActivityLatestAnnouncementvBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class LatestAnnouncementActivity :
    ViewModelActivity<MineViewModel, ActivityLatestAnnouncementvBinding>() {
    override fun createBinding() = ActivityLatestAnnouncementvBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LatestAnnouncementActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val mAdapter by lazy { AnnouncementAdapter() }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvAnnouncement) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf("", "", ""))
    }
}