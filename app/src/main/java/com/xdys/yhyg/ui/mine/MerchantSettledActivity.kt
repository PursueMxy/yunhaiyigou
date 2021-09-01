package com.xdys.yhyg.ui.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.databinding.ActivityMerchantSettledBinding
import com.xdys.yhyg.vm.MineViewModel

class MerchantSettledActivity : ViewModelActivity<MineViewModel, ActivityMerchantSettledBinding>() {
    override fun createBinding() = ActivityMerchantSettledBinding.inflate(layoutInflater)
    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MerchantSettledActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        tvInOnline.setOnClickListener {
            OnlineMerchantSettledActivity.start(this@MerchantSettledActivity)
        }
        tvInOffline.setOnClickListener {
            OfflineMerchantSettledActivity.start(this@MerchantSettledActivity)
        }
    }

}