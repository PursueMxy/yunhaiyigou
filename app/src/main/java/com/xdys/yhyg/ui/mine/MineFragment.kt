package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.config.Constant
import com.xdys.library.extension.loadCircleImage
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentMineBinding
import com.xdys.yhyg.entity.mine.UserInfoEntity
import com.xdys.yhyg.popup.PersonalSharePopupWindow
import com.xdys.yhyg.ui.order.MyOrderActivity
import com.xdys.yhyg.ui.sale.ReturnAfterSaleActivity
import com.xdys.yhyg.ui.sale.ViewSolutionActivity
import com.xdys.yhyg.ui.setting.SetActivity
import com.xdys.yhyg.ui.web.WebViewActivity
import com.xdys.yhyg.vm.MineViewModel

class MineFragment : ViewModelFragment<MineViewModel, FragmentMineBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMineBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        clShopkeeperCenter.setOnClickListener {
            ShopkeeperCenterActivity.start(requireContext())
        }
        tvAllOrder.setOnClickListener {
            MyOrderActivity.start(requireContext())
        }
        tvAddressManagement.setOnClickListener {
            AddressActivity.edit(requireContext())
        }
        tvNotification.setOnClickListener {
            MessageNotificationActivity.start(requireContext())
        }
        tvFeedback.setOnClickListener {
            FeedbackAddActivity.start(requireContext())
        }
        tvMyCollection.setOnClickListener {
            CollectionActivity.start(requireContext())
        }
        tvInvitationQRCode.setOnClickListener {
            popupShare.showPopupWindow()
        }
        titleBar.setOnRightClickListener {
            SetActivity.start(requireContext())
        }
        llOnlinePointsNum.setOnClickListener {
            LineIntegralActivity.start(requireContext())
        }
        tvMerchantSettled.setOnClickListener {
            MerchantSettledActivity.start(requireContext())
        }
        flBalance.setOnClickListener {
            MyBalanceActivity.start(requireContext())
        }
        tvMyCardVoucher.setOnClickListener {
            MyCouponsActivity.start(requireContext())
        }
        tvMyTeam.setOnClickListener {
            MyTeamActivity.start(requireContext())
        }
        tvReturnAfterSale.setOnClickListener {
            ReturnAfterSaleActivity.start(requireContext())
        }
        tvFeedback.setOnClickListener {
            ViewSolutionActivity.start(requireContext())
        }
        tvBoardCommittee.setOnClickListener {
        }
        tvCommonProblem.setOnClickListener {
            WebViewActivity.start(requireContext(), "${Constant.webUrl}/problem", "常见问题")
        }
    }

    fun fillUI(userInfo: UserInfoEntity) {
        with(binding) {
            tvMemberLevel.text = "普通会员"
            tvUserId.text = "ID:${userInfo.userCode}"
            tvUserName.text = userInfo.nickName
            tvOnlinePointsNum.text = userInfo.onlineIntegral
            tvOfflinePointsNum.text = userInfo.offlineIntegral
            tvBalanceNum.text = userInfo.balance
            tvCouponsNumber.text = userInfo.cardVoucherList.size.toString()
            ivPortrait.loadCircleImage(R.mipmap.watch)
            tvTodayIncomeNum.text = "16586"
            tvTodayOrderNum.text = "5556"
            tvTodaySalesNum.text = "7596"
            ivPortrait.loadCircleImage(userInfo.headimgUrl)
            clShopkeeperCenter.isVisible = userInfo.isMerchant == "1"
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

    private val popupShare: PersonalSharePopupWindow by lazy {
        PersonalSharePopupWindow(requireContext()) {}
    }
}