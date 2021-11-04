package com.xdys.yhyg.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.library.widget.SpanView
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.home.FlashSaleAdapter
import com.xdys.yhyg.databinding.ActivityFlashSaleBinding
import com.xdys.yhyg.entity.goods.ConfirmOrderEntity
import com.xdys.yhyg.entity.goods.FoldGoods
import com.xdys.yhyg.entity.goods.FoldOrder
import com.xdys.yhyg.entity.goods.OrderGoods
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
import com.xdys.yhyg.ui.order.ConfirmOrderActivity
import com.xdys.yhyg.vm.HomeViewModel
import java.text.DecimalFormat
import java.time.Instant

class FlashSaleActivity : ViewModelActivity<HomeViewModel, ActivityFlashSaleBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, FlashSaleActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val format = DecimalFormat("00")
    override fun createBinding() = ActivityFlashSaleBinding.inflate(layoutInflater)

    override val viewModel: HomeViewModel by viewModels()

    val mAdapter by lazy { FlashSaleAdapter() }

    override fun initUI(savedInstanceState: Bundle?): Unit = with(binding) {
        with(rvFlashGoods) {
            adapter = mAdapter
        }
        with(mAdapter) {
            setOnItemChildClickListener { _, view, position ->
                when (view.id) {
                    R.id.tvGoPanicBuying -> {
                        val goodsSale = data[position]
                        if (goodsSale.seckillNum < goodsSale.limitNum) {
                            ConfirmOrderActivity.goodsStart(
                                this@FlashSaleActivity,
                                FoldOrder(
                                    "", mutableListOf(
                                        FoldGoods(
                                            goodsSale?.shopId.toString(),
                                            goodsSale.spuId,
                                            goodsSale.skuId,
                                            "1",
                                            goodsSale.seckillPrice,
                                            "3",
                                            "1"
                                        )
                                    )
                                )
                            )
                        }
                    }
                }
            }

            setOnItemClickListener { _, view, position ->
                data[position].spuId?.let {
                    viewModel.seckillHallLiveData.value?.records?.get(0)?.endHallTime?.let { it1 ->
                        GoodsDetailActivity.start(
                            this@FlashSaleActivity,
                            it,
                            1,
                            it1.div(1000).toInt()
                        )
                    }
                }
            }
        }
        refreshLayout.setOnRefreshListener { initData() }
    }

    override fun initData() {
        viewModel.seckillHall()
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.seckillHallLiveData.observe(this) {
            binding.refreshLayout.finishRefresh()
            mAdapter.setDiffNewData(it.records.get(0).seckillGoods)
            fillCountdown()
            viewModel.countdown(100000)
        }
        viewModel.countdownLiveData.observe(this, {
            fillCountdown()
        })
    }


    fun fillCountdown() {
        viewModel.seckillHallLiveData.value?.let {
            val endTime = it.records.get(0).endHallTime?.div(1000)
            var needCountdown =
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Instant.now().epochSecond
                } else {
                    System.currentTimeMillis() / 1000
                }
            if (endTime!! > needCountdown) {
                val hour = format.format((endTime?.minus(needCountdown))?.div((60 * 60)))
                val min = format.format((endTime?.minus(needCountdown))?.rem(60 * 60)?.div(60))
                val sec = format.format((endTime?.minus(needCountdown))?.rem(60))
                val str = " $hour : $min : $sec "
                val builder = SpannableStringBuilder(str)
                builder.setSpan(
                    SpanView(
                        ContextCompat.getColor(this, R.color.RE3),
                        ContextCompat.getColor(this, R.color.white)
                    ),
                    0, 2 + 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
                builder.setSpan(
                    SpanView(
                        ContextCompat.getColor(this, R.color.RE3),
                        ContextCompat.getColor(this, R.color.white)
                    ),
                    str.length - 9, str.length - 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
                builder.setSpan(
                    SpanView(
                        ContextCompat.getColor(this, R.color.RE3),
                        ContextCompat.getColor(this, R.color.white)
                    ),
                    str.length - 4, str.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
                binding.tvUntilTime.text = builder
            } else {
                binding.tvUntilTime.text = "活动已结束"
            }

        }
    }
}