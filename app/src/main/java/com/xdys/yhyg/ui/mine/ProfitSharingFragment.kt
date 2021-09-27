package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.adapte.mine.ProfitSharingAdapter
import com.xdys.yhyg.databinding.FragmentProfitSharingBinding
import com.xdys.yhyg.entity.mine.ProfitSharEntity
import com.xdys.yhyg.vm.MineViewModel

class ProfitSharingFragment : ViewModelFragment<MineViewModel, FragmentProfitSharingBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfitSharingBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    private val mAdapter by lazy { ProfitSharingAdapter() }

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = ProfitSharingFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvProfitSharing) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setDiffNewData(mutableListOf(ProfitSharEntity()))
    }
}