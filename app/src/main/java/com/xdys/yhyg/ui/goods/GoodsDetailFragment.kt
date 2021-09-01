package com.xdys.yhyg.ui.goods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.xdys.yhyg.databinding.FragmentGoodsDetailBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.extension.dp
import com.xdys.library.extension.loadCircleImage
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration

class GoodsDetailFragment : ViewModelFragment<MineViewModel, FragmentGoodsDetailBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGoodsDetailBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by viewModels()

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
        tvPrice.text = "￥899.00"
        tvSold.text = "已售 233444"
        tvGoodsName.text = "杜康酒【迎雙喜】500ML纯粮食酿造浓香型白酒"
        tvIntroduce.text = "杜康酒【迎雙喜】婚礼御用酒 满月酒 喜宴用酒"
        tvSelected.text = "【迎雙喜】500ML单瓶，1件"
        tvDelivery.text = "商家配送"
        ivPortrait.loadCircleImage(R.mipmap.du_kang_jiu)
        tvUserName.text = "小杜小杜"
        tvTime.text = "2021-06-07  9:27"
        rating.rating = 3F
        tvSpecification.text = "规格：【迎雙喜】500ML单瓶"
        tvContent.text = "包装设计精美，大气沉稳上档次，口感也不错，物流也很快，一次不错的购物体验！"
        tvSpecification.text = "规格：【迎雙喜】500ML单瓶"
        tvViewAll.setOnClickListener {
            navController.navigate(R.id.goodsEvaluateFragment)
        }
    }

    override fun initData() {
        goodsBannerAdapter.setNewInstance(
            mutableListOf(
                "https://img1.baidu.com/it/u=1825851994,4163570429&fm=253&fmt=auto&app=120&f=JPEG?w=934&h=500",
                "https://img2.baidu.com/it/u=3495436499,1211422207&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=251614576,2693916083&fm=26&fmt=auto&gp=0.jpg",
                "https://img0.baidu.com/it/u=2027992389,3130985476&fm=26&fmt=auto&gp=0.jpg"
            )
        )
        getCouponsAdapter.setNewInstance(mutableListOf("满500 送150 ", "满1230 送 333"))
        guaranteeAdapter.setNewInstance(mutableListOf("厂商发货配送", "品质保证", "不支持 7 天无理由退货"))
        evaluateImgAdapter.setNewInstance(mutableListOf("", "", ""))
    }

}