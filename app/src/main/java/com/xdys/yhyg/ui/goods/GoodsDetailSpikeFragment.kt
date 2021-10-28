package com.xdys.yhyg.ui.goods

import android.graphics.Paint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.flexbox.FlexboxLayoutManager
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.home.EvaluateImgAdapter
import com.xdys.yhyg.adapte.home.GetCouponsAdapter
import com.xdys.yhyg.adapte.home.GoodsBannerAdapter
import com.xdys.yhyg.adapte.home.GuaranteeAdapter
import com.xdys.yhyg.databinding.FragmentGoodsDetailSpikeBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.config.Constant
import com.xdys.library.extension.currency
import com.xdys.library.extension.dp
import com.xdys.library.extension.loadCircleImage
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.library.widget.SpanView
import com.xdys.yhyg.entity.goods.GoodsDetailEntity
import com.xdys.yhyg.vm.HomeViewModel
import java.text.DecimalFormat
import java.time.Instant

class GoodsDetailSpikeFragment :
    ViewModelFragment<HomeViewModel, FragmentGoodsDetailSpikeBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGoodsDetailSpikeBinding.inflate(inflater, container, false)

    private val goodsBannerAdapter by lazy { GoodsBannerAdapter() }

    private val guaranteeAdapter by lazy { GuaranteeAdapter() }

    private val getCouponsAdapter by lazy { GetCouponsAdapter() }

    private val evaluateImgAdapter by lazy { EvaluateImgAdapter() }

    override val viewModel: HomeViewModel by activityViewModels()

    private val navController by lazy { findNavController() }
    private val format = DecimalFormat("00")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        webView.loadUrl("file:///android_asset/local/local.html")
        with(bannerContainer) {
            adapter = goodsBannerAdapter
            setOuterPageChangeListener(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    tvItem.text = "${position + 1}/4"
                }
            })
        }
        with(rvGuarantee) {
            layoutManager = FlexboxLayoutManager(context)
            adapter = guaranteeAdapter
        }
        with(rvGetCoupons) {
            adapter = getCouponsAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        with(rvEvaluate) {
            adapter = evaluateImgAdapter
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(DividerItemDecoration(13.dp, 10.px))
        }
        tvViewAll.setOnClickListener {
            navController.navigate(R.id.goodsEvaluateFragment)
        }
    }

    override fun initData() {
        activity?.intent?.getStringExtra(Constant.Key.EXTRA_ID)?.let {
            viewModel.goodsDetail(it)
            viewModel.ensureBySpuId(it)
            viewModel.goodsSpu(it)
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.goodsDetailLiveData.observe(this) {
            fillUI(it)
            viewModel.countdown(100000)
        }
        viewModel.countdownLiveData.observe(this, {
            fillCountdown()
        })
    }

    fun fillUI(goods: GoodsDetailEntity) {
        with(binding) {
            tvGoodsName.text = goods.name
            tvIntroduce.text = ""
            tvDelivery.text = "商家配送"
            ivPortrait.loadCircleImage(R.mipmap.du_kang_jiu)
            tvUserName.text = "小杜小杜"
            tvTime.text = "2021-06-07  9:27"
            rating.rating = 3F
            tvSpecification.text = "规格：【迎雙喜】500ML单瓶"
            tvContent.text = "包装设计精美，大气沉稳上档次，口感也不错，物流也很快，一次不错的购物体验！"
            tvSpecification.text = "规格：【迎雙喜】500ML单瓶"
            tvOriginalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            tvPriceSpike.text = goods.priceDown?.currency()
            tvOriginalPrice.text = "￥${goods.priceUp}"
            goodsBannerAdapter.setNewInstance(goods.picUrls)
            webView.loadUrl("javascript:callJS('${goods.description}')")
            fillCountdown()
        }
    }

    fun fillCountdown() {
        activity?.intent?.getIntExtra(Constant.Key.EXTRA_DATA, 0)?.let {
            var needCountdown =
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Instant.now().epochSecond
                } else {
                    System.currentTimeMillis() / 1000
                }
            if (it.toLong() > needCountdown) {
                val hour = format.format((it.toLong()?.minus(needCountdown))?.div((60 * 60)))
                val min = format.format((it?.toLong().minus(needCountdown))?.rem(60 * 60)?.div(60))
                val sec = format.format((it?.toLong().minus(needCountdown))?.rem(60))
                val str = " $hour : $min : $sec "
                val builder = SpannableStringBuilder(str)
                builder.setSpan(
                    SpanView(
                        ContextCompat.getColor(requireContext(), R.color.white),
                        ContextCompat.getColor(requireContext(), R.color.RE3)
                    ),
                    0, 2 + 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
                builder.setSpan(
                    SpanView(
                        ContextCompat.getColor(requireContext(), R.color.white),
                        ContextCompat.getColor(requireContext(), R.color.RE3)
                    ),
                    str.length - 9, str.length - 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
                builder.setSpan(
                    SpanView(
                        ContextCompat.getColor(requireContext(), R.color.white),
                        ContextCompat.getColor(requireContext(), R.color.RE3)
                    ),
                    str.length - 4, str.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
                binding.tvUntilTime.text = builder
            } else {
                binding.tvUntilTime.text = "已结束"
            }

        }
    }
}