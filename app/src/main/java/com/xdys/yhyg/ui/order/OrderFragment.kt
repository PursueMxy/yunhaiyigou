package com.xdys.yhyg.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.xdys.yhyg.adapte.order.OrderAdapter
import com.xdys.yhyg.databinding.FragmentOrderBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class OrderFragment : ViewModelFragment<MineViewModel, FragmentOrderBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentOrderBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = OrderFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    private val orderAdapter by lazy { OrderAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvOrder) {
            adapter = orderAdapter
            orderAdapter.setOnItemClickListener { _, _, position ->
                OrderDetailActivity.start(requireContext())
            }
        }
    }

    override fun initData() {
        orderAdapter.setNewInstance(mutableListOf("", "", ""))
    }
}