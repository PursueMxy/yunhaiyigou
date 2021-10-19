package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.loadCircleImage
import com.xdys.library.extension.singleTop
import com.xdys.library.utils.PhotoUtils
import com.xdys.yhyg.databinding.ActivityPersonalInformationBinding
import com.xdys.yhyg.entity.mine.UserInfoEntity
import com.xdys.yhyg.vm.MineViewModel

class PersonalInformationActivity :
    ViewModelActivity<MineViewModel, ActivityPersonalInformationBinding>() {
    override fun createBinding() = ActivityPersonalInformationBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, PersonalInformationActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        flNickName.setOnClickListener {
            ChangeNicknameActivity.start(this@PersonalInformationActivity)
        }
        ivAvatar.setOnClickListener {
            choose()
        }
    }

    override fun initData() {
        viewModel.userInfo()
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.userInfoLivaData.observe(this) {
            fillUI(it)
        }
    }

    private fun fillUI(userInfo: UserInfoEntity) {
        with(binding) {
            tvNickName.text = userInfo.nickName
            tvBirthday.text = "2000.12.23"
            tvGender.text = when (userInfo.sex) {
                "0" -> "男"
                "1" -> "女"
                else -> "未知"
            }
            tvRegion.text = userInfo.province + userInfo.city + userInfo.towns
            ivAvatar.loadCircleImage(userInfo.headimgUrl)
        }
    }


    private fun choose() {
        PhotoUtils.selectPicture(this, object : OnResultCallbackListener<LocalMedia?> {
            override fun onResult(result: MutableList<LocalMedia?>?) {
                result?.get(0)?.let {
                    binding.ivAvatar.loadCircleImage(it.realPath)
                }
            }

            override fun onCancel() {
            }
        })
    }
}