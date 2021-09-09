package com.xdys.yhyg.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hjq.toast.ToastUtils
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.extension.dp
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.adapte.home.SecondsKillAdapter
import com.xdys.yhyg.adapte.home.VerticalGoodsAdapter
import com.xdys.yhyg.databinding.FragmentBrandZoneBinding
import com.xdys.yhyg.vm.HomeViewModel

class BrandZoneFragment : ViewModelFragment<HomeViewModel, FragmentBrandZoneBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBrandZoneBinding.inflate(inflater, container, false)

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = BrandZoneFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    private val mAdapter by lazy { SecondsKillAdapter() }

    private val verticalAdapter by lazy { VerticalGoodsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvGoods) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        with(rvVerticalGoods) {
            adapter = verticalAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(DividerItemDecoration(0.dp, 11.px))
        }

    }

    override fun initObserver() {
        viewModel.conversionLiveData.observe(this) {
            if (it) {
                binding.rvVerticalGoods.visibility = View.VISIBLE
                binding.rvGoods.visibility = View.GONE
                verticalAdapter.setNewInstance(mutableListOf("", "", "", ""))
                verticalAdapter.notifyDataSetChanged()
            } else {
                binding.rvVerticalGoods.visibility = View.GONE
                binding.rvGoods.visibility = View.VISIBLE
                mAdapter.setNewInstance(mutableListOf("", "", "", "", ""))
                mAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf("", "", "", "", ""))
    }

    override val viewModel: HomeViewModel by activityViewModels()
}