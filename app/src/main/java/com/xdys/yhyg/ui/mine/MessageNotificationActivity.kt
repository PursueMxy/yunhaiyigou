package com.xdys.yhyg.ui.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.mine.NotificationAdapter
import com.xdys.yhyg.databinding.ActivityMessageNotificationBinding
import com.xdys.yhyg.entity.mine.NotificationEntity
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop

class MessageNotificationActivity :
    ViewModelActivity<MineViewModel, ActivityMessageNotificationBinding>() {
    override fun createBinding() = ActivityMessageNotificationBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MessageNotificationActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val mAdapter by lazy { NotificationAdapter() }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvNotification) {
            adapter = mAdapter
        }
        with(mAdapter) {
            setOnItemClickListener { _, _, position ->
                when (position) {
                    0 -> MallActivitiesActivity.start(this@MessageNotificationActivity)
                    1 -> TransactionNotificationActivity.start(this@MessageNotificationActivity)
                    2 -> LogisticsNoticeActivity.start(this@MessageNotificationActivity)
                    3 -> DiscountCouponActivity.start(this@MessageNotificationActivity)
                    4 -> LatestAnnouncementActivity.start(this@MessageNotificationActivity)
                }

            }
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(
            mutableListOf(
                NotificationEntity(
                    getString(R.string.mall_activities),
                    R.mipmap.mall_activities
                ),
                NotificationEntity(
                    getString(R.string.transaction_notification),
                    R.mipmap.transaction_notification
                ),
                NotificationEntity(
                    getString(R.string.logistics_notice),
                    R.mipmap.logistics_notice
                ),
                NotificationEntity(
                    getString(R.string.discount_coupon),
                    R.mipmap.discount_coupon
                ),
                NotificationEntity(
                    getString(R.string.latest_announcement),
                    R.mipmap.latest_announcement
                ),
            )
        )
    }
}