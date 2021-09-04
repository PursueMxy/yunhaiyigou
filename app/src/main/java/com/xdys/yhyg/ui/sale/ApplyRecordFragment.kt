package com.xdys.yhyg.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.sale.ApplyRecordAdapter
import com.xdys.yhyg.databinding.FragmentApplyRecordBinding
import com.xdys.yhyg.vm.AfterSaleViewModel

class ApplyRecordFragment : ViewModelFragment<AfterSaleViewModel, FragmentApplyRecordBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentApplyRecordBinding.inflate(inflater, container, false)

    override val viewModel: AfterSaleViewModel by activityViewModels()

    private val mAdapter by lazy { ApplyRecordAdapter() }

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = ApplyRecordFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        with(rvRecord) {
            adapter = mAdapter
        }
        with(mAdapter) {
            setEmptyView(R.layout.empty_no_order_record)
            setOnItemClickListener { _, _, position ->
                ServiceOrderDetailsActivity.start(requireContext())
            }
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf("", "", ""))
    }
}
