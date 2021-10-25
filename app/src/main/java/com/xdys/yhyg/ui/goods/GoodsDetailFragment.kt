package com.xdys.yhyg.ui.goods

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.flexbox.FlexboxLayoutManager
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.config.Constant
import com.xdys.library.extension.currency
import com.xdys.library.extension.dp
import com.xdys.library.extension.loadCircleImage
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.home.EvaluateImgAdapter
import com.xdys.yhyg.adapte.home.GetCouponsAdapter
import com.xdys.yhyg.adapte.home.GoodsBannerAdapter
import com.xdys.yhyg.adapte.home.GuaranteeAdapter
import com.xdys.yhyg.databinding.FragmentGoodsDetailBinding
import com.xdys.yhyg.entity.goods.GoodsDetailEntity
import com.xdys.yhyg.vm.HomeViewModel

class GoodsDetailFragment : ViewModelFragment<HomeViewModel, FragmentGoodsDetailBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGoodsDetailBinding.inflate(inflater, container, false)

    override val viewModel: HomeViewModel by activityViewModels()

    private val goodsBannerAdapter by lazy { GoodsBannerAdapter() }

    private val guaranteeAdapter by lazy { GuaranteeAdapter() }

    private val getCouponsAdapter by lazy { GetCouponsAdapter() }

    private val evaluateImgAdapter by lazy { EvaluateImgAdapter() }

    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        binding.webView.loadUrl("file:///android_asset/local/local.html")
        with(bannerContainer) {
            adapter = goodsBannerAdapter
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
        }
        getCouponsAdapter.setNewInstance(mutableListOf("满500 送150 ", "满1230 送 333"))
        guaranteeAdapter.setNewInstance(mutableListOf("厂商发货配送", "品质保证", "不支持 7 天无理由退货"))
        evaluateImgAdapter.setNewInstance(mutableListOf("", "", ""))
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun fillUI(goods: GoodsDetailEntity) {
        with(binding) {
            goodsBannerAdapter.setNewInstance(goods.picUrls)
            tvPrice.text = goods.priceUp?.currency()
            tvSold.text = "已售: ${goods.saleNum}"
            tvGoodsName.text = goods.name
            tvIntroduce.text = ""
            tvSelected.text = goods.specType
            tvDelivery.text = "商家配送"
            ivPortrait.loadCircleImage(R.mipmap.du_kang_jiu)
            val jhhh: String = goods.description.toString()
            webView.loadUrl("javascript:callJS('$jhhh')")
            tvUserName.text = "小杜小杜"
            tvTime.text = "2021-06-07  9:27"
            rating.rating = 3F
            tvSpecification.text = "规格：【迎雙喜】500ML单瓶"
            tvContent.text = "包装设计精美，大气沉稳上档次，口感也不错，物流也很快，一次不错的购物体验！"
            tvSpecification.text = "规格：【迎雙喜】500ML单瓶"
            bannerContainer.setOuterPageChangeListener(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    tvItem.text = "${position + 1}/${goods.picUrls.size}"
                }
            })
        }
    }


    override fun initObserver() {
        super.initObserver()
        viewModel.goodsDetailLiveData.observe(this) {
            fillUI(it)
        }
    }

}