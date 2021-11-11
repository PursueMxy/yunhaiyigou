package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.Formatter
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant
import com.xdys.library.extension.singleTop
import com.xdys.library.utils.FileUtils
import com.xdys.yhyg.databinding.ActivitySetBinding
import com.xdys.yhyg.popup.PromptPopupWindow
import com.xdys.yhyg.ui.login.LoginActivity
import com.xdys.yhyg.vm.SetViewModel

class SetActivity : ViewModelActivity<SetViewModel, ActivitySetBinding>() {
    override fun createBinding() = ActivitySetBinding.inflate(layoutInflater)

    override val viewModel: SetViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SetActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        clPersonal.setOnClickListener {
            PersonalInformationActivity.start(this@SetActivity)
        }
        tvAccountSecurity.setOnClickListener {
            AccountSecurityActivity.start(this@SetActivity)
        }
        tvLogOut.setOnClickListener {
            viewModel.logout()
            popupFeedback.setData("您确定要退出登录么").showPopupWindow()
        }
        flAbout.setOnClickListener {
            AboutActivity.start(this@SetActivity)
        }
        flClearCache.setOnClickListener {
            popupClearCache.setData("您确定清除缓存么").showPopupWindow()
        }
    }

    private val popupFeedback: PromptPopupWindow by lazy {
        PromptPopupWindow(this) {
            Constant.saveUserToken("")
            LoginActivity.startActivity(this, true)
        }
    }


    private val popupClearCache: PromptPopupWindow by lazy {
        PromptPopupWindow(this) {
            if (FileUtils.deleteFile(cacheDir)) {
                binding.tvClearCache.text =
                    Formatter.formatFileSize(this, FileUtils.getFolderSize(cacheDir))
            }
        }
    }
}