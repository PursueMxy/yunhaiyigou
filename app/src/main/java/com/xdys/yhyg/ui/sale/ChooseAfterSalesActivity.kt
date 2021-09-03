package com.xdys.yhyg.ui.sale

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.ActivityChooseAfterSalesBinding
import com.xdys.yhyg.vm.AfterSaleViewModel

class ChooseAfterSalesActivity :
    ViewModelActivity<AfterSaleViewModel, ActivityChooseAfterSalesBinding>() {
    override fun createBinding() = ActivityChooseAfterSalesBinding.inflate(layoutInflater)

    override val viewModel: AfterSaleViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ChooseAfterSalesActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }


    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        ivGoods.loadRoundCornerImage(R.mipmap.du_kang_jiu)
        tvOrderStatus.text = "中通快递：【厦门市】快递已送达【厦门戏..."
    }
}