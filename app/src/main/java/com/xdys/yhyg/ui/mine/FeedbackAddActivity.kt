package com.xdys.yhyg.ui.mine

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.xdys.yhyg.databinding.ActivityFeedbackAddBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class FeedbackAddActivity : ViewModelActivity<MineViewModel, ActivityFeedbackAddBinding>() {

    override fun createBinding() = ActivityFeedbackAddBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, FeedbackAddActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }
}