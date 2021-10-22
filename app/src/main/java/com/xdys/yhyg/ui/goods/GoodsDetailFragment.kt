package com.xdys.yhyg.ui.goods

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
        binding.webView.loadUrl("file:///android_asset/local/local.html")
        tvViewAll.setOnClickListener {
            navController.navigate(R.id.goodsEvaluateFragment)
        }
    }

    override fun initData() {
        getCouponsAdapter.setNewInstance(mutableListOf("满500 送150 ", "满1230 送 333"))
        guaranteeAdapter.setNewInstance(mutableListOf("厂商发货配送", "品质保证", "不支持 7 天无理由退货"))
        evaluateImgAdapter.setNewInstance(mutableListOf("", "", ""))
    }

    fun fillUI(goods: GoodsDetailEntity) {
        with(binding) {
            goodsBannerAdapter.setNewInstance(goods.picUrls)
            tvPrice.text = goods.priceUp
            tvSold.text = "已售: ${goods.saleNum}"
            tvGoodsName.text = goods.name
            tvIntroduce.text = ""
            tvSelected.text = goods.specType
            tvDelivery.text = "商家配送"
            ivPortrait.loadCircleImage(R.mipmap.du_kang_jiu)
            val jhhh: String =
                "<p><img src=\"http://img20.360buyimg.com/vc/jfs/t1/78696/16/11566/195886/5d8daf86Efebb0a96/1797cdfc57bed506.jpg\">&nbsp;<img src=\"http://img20.360buyimg.com/vc/jfs/t1/47676/8/16789/186151/5ddcfe7aE972ab628/4d2949b425b32212.jpg\"></p>"
            goods.description?.let {
                Log.e("所倡导的", it)
                webView.loadUrl("javascript:callJS('$jhhh')")
            }
            tvUserName.text = "小杜小杜"
            tvTime.text = "2021-06-07  9:27"
            rating.rating = 3F
            tvSpecification.text = "规格：【迎雙喜】500ML单瓶"
            tvContent.text = "包装设计精美，大气沉稳上档次，口感也不错，物流也很快，一次不错的购物体验！"
            tvSpecification.text = "规格：【迎雙喜】500ML单瓶"
        }
    }


    override fun initObserver() {
        super.initObserver()
        viewModel.goodsDetailLiveData.observe(this) {
            fillUI(it)
        }
    }

}