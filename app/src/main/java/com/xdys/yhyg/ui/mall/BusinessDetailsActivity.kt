package com.xdys.yhyg.ui.mall

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.ActivityBusinessDetailsBinding
import com.xdys.yhyg.popup.MakeCallPopupWindow
import com.xdys.yhyg.popup.SharePopupWindow
import com.xdys.yhyg.vm.MallViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.ui.web.WebViewActivity

class BusinessDetailsActivity : ViewModelActivity<MallViewModel, ActivityBusinessDetailsBinding>() {
    override fun createBinding() = ActivityBusinessDetailsBinding.inflate(layoutInflater)

    override val viewModel: MallViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, BusinessDetailsActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?): Unit = with(binding) {
        tvPayMerchant.setOnClickListener {
            CheckoutCounterActivity.start(this@BusinessDetailsActivity)
        }
        titleBar.setOnRightClickListener {
            popupShare.showPopupWindow()
        }
        tvContactMerchant.setOnClickListener {
            popupMakeCall.showPopupWindow()
        }
        tvLicenseInformation.setOnClickListener {
            WebViewActivity.start(this@BusinessDetailsActivity,"${Constant.webUrl}/verify")
        }
        ivLogo.loadRoundCornerImage(R.mipmap.business_tu)
        tvBusinessName.text = "上官糖炒栗子·四果汤(塔埔店)"
        tvBusinessHours.text = "营业时间：9:00~22:00 （周一至周日）"
        tvAddress.text = "厦门市思明区观音山塔埔路新中街101-1店面"
        tvBusinessDetail.text =
            "四果汤是一道美味可口的名点，发源于福建闽南地区。味甜爽口，清凉解毒。在很多代人的记忆里都离不开一种伴随着炎夏和蝉声的爽口甜蜜，那种爽口香甜源自于一碗流传已久的四果汤四果汤历史悠久，系福建闽南一带非常出名的特色小吃，兴于泉州、漳州一带，具有祛暑降温的作用，因而在夏季备受人们喜爱。每至炎夏，或是街边小摊，或是老字号店铺，人们总是喜欢适时地叫上一碗四果汤，消却炎炎夏日的闷热。"

    }

    private val popupShare: SharePopupWindow by lazy {
        SharePopupWindow(this) { position ->
        }
    }

    private val popupMakeCall: MakeCallPopupWindow by lazy {
        MakeCallPopupWindow(this) {
        }
    }
}