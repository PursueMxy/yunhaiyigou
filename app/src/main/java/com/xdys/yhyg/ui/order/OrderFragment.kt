package com.xdys.yhyg.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.order.OrderAdapter
import com.xdys.yhyg.databinding.FragmentOrderBinding
import com.xdys.yhyg.vm.OrderViewModel

class OrderFragment : ViewModelFragment<OrderViewModel, FragmentOrderBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentOrderBinding.inflate(inflater, container, false)

    override val viewModel: OrderViewModel by activityViewModels()

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = OrderFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    private val orderAdapter by lazy { OrderAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        with(rvOrder) {
            adapter = orderAdapter

        }
        with(orderAdapter) {
            setEmptyView(R.layout.layout_empty_order)
            setOnItemClickListener { _, _, position ->
                data[position].orders_id?.let { OrderDetailActivity.start(requireContext(), it) }
            }
        }
        refreshLayout.setOnRefreshListener { initData() }
    }

    override fun initData() {
        var position = arguments?.getInt(EXTRA_ID, 0)
        when (position) {
            0 -> viewModel.orderList("0", true)
            1 -> viewModel.orderList("11", true)
            2 -> viewModel.orderList("1", true)
            3 -> viewModel.orderList("2", true)
            4 -> viewModel.orderList("5", true)
            5 -> viewModel.orderList("3", true)
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.listStatusLiveData.observe(viewLifecycleOwner) {
            it.restoreView(binding.refreshLayout, orderAdapter)
        }
        viewModel.orderListLiveData.observe(viewLifecycleOwner) {
            binding.refreshLayout.finishRefresh()
            orderAdapter.setDiffNewData(it)
        }
    }
}