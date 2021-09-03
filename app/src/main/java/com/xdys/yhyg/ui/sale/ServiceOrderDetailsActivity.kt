package com.xdys.yhyg.ui.sale

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.dp
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.library.extension.singleTop
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.sale.IssueAdapter
import com.xdys.yhyg.adapte.sale.ServiceOrderStatusAdapter
import com.xdys.yhyg.databinding.ActivityServiceOrderDetailsBinding
import com.xdys.yhyg.vm.AfterSaleViewModel

class ServiceOrderDetailsActivity :
    ViewModelActivity<AfterSaleViewModel, ActivityServiceOrderDetailsBinding>() {
    override fun createBinding() = ActivityServiceOrderDetailsBinding.inflate(layoutInflater)

    override val viewModel: AfterSaleViewModel by viewModels()

    private val mAdapter by lazy { ServiceOrderStatusAdapter() }

    private val issueAdapter by lazy { IssueAdapter() }


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ServiceOrderDetailsActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }


    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvStatus) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        with(rvIssue) {
            adapter = issueAdapter
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(DividerItemDecoration(9.dp, 9.dp))
            tvName.text = "酒祖杜康12窖区 窖龄40年 50度浓香型白酒500ml单瓶酒盒装..."
            tvUnitPrice.text = "单价：￥368.00"
            tvNumberApplications.text = "申请数量：1"
            ivGoods.loadRoundCornerImage(R.mipmap.du_kang_jiu)
            tvOrderNumber.text = "422485754"
            tvApplicationTime.text = "2022-05-17  10:22:11  "
            tvServiceType.text = "退货退款"
            tvReasonApplication.text = "商品与描述不符"
            tvRefundMethod.text = "原返"
            tvExpectedRefundTo.text = "微信零钱"
            tvPrice.text = "¥588"
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf("", "", ""))
        issueAdapter.setNewInstance(mutableListOf("", "", ""))
    }
}