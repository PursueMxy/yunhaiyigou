package com.xdys.yhyg.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xdys.yhyg.adapte.home.SpikeAdapter
import com.xdys.yhyg.databinding.FragmentCommodityTypeBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class CommodityTypeFragment : ViewModelFragment<MineViewModel, FragmentCommodityTypeBinding>() {

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = CommodityTypeFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCommodityTypeBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by viewModels()

    private val spikeAdapter by lazy { SpikeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvGoods) {
            adapter = spikeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun initData() {
        spikeAdapter.setNewInstance(mutableListOf("", "", "", "", ""))
    }
}