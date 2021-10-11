package com.xdys.yhyg.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.adapte.home.CouponCenterAdapter
import com.xdys.yhyg.databinding.FragmentCouponCenterBinding
import com.xdys.yhyg.entity.home.CouponCenterEntity
import com.xdys.yhyg.vm.HomeViewModel

class CouponCenterFragment : ViewModelFragment<HomeViewModel, FragmentCouponCenterBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCouponCenterBinding.inflate(inflater, container, false)

    override val viewModel: HomeViewModel by viewModels()


    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = CouponCenterFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    private val mAdapter by lazy { CouponCenterAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvCoupon) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setDiffNewData(
            mutableListOf(
                CouponCenterEntity(),
                CouponCenterEntity(),
                CouponCenterEntity()
            )
        )
    }
}