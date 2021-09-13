package com.xdys.yhyg.ui.order

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.databinding.ActivityConfirmOrderBinding
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
import com.xdys.yhyg.vm.CartViewModel

class ConfirmOrderActivity : ViewModelActivity<CartViewModel, ActivityConfirmOrderBinding>() {

    override fun createBinding() = ActivityConfirmOrderBinding.inflate(layoutInflater)

    override val viewModel: CartViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ConfirmOrderActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }
}