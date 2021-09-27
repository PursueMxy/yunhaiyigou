package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.adapte.mine.WithdrawalsRecordAdapter
import com.xdys.yhyg.databinding.FragmentWithdrawalsRecordBinding
import com.xdys.yhyg.entity.mine.WithdrawalsRecordEntity
import com.xdys.yhyg.vm.MineViewModel

class WithdrawalsRecordFragment :
    ViewModelFragment<MineViewModel, FragmentWithdrawalsRecordBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentWithdrawalsRecordBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = WithdrawalsRecordFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    private val mAdapter by lazy { WithdrawalsRecordAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvWithdrawalsRecord) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf(WithdrawalsRecordEntity()))
    }
}