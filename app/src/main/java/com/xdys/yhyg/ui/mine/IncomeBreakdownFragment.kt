package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.adapte.mine.IncomeBreakdownAdapter
import com.xdys.yhyg.databinding.FragmentIncomeBreakdownBinding
import com.xdys.yhyg.vm.MineViewModel

class IncomeBreakdownFragment : ViewModelFragment<MineViewModel, FragmentIncomeBreakdownBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentIncomeBreakdownBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = IncomeBreakdownFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    private val mAdapter by lazy { IncomeBreakdownAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvIncomeBreakdown) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf("", "", ""))
    }
}