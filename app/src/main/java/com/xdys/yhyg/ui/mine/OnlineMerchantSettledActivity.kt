package com.xdys.yhyg.ui.mine

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.databinding.ActivityOnlineMerchantsSettledBinding
import com.xdys.yhyg.vm.MineViewModel

class OnlineMerchantSettledActivity :
    ViewModelActivity<MineViewModel, ActivityOnlineMerchantsSettledBinding>() {
    override fun createBinding() = ActivityOnlineMerchantsSettledBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, OnlineMerchantSettledActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }
}