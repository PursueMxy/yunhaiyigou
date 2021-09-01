package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.adapte.mine.PointsDetailAdapter
import com.xdys.yhyg.databinding.FragmentPointsDetailsBinding
import com.xdys.yhyg.vm.MineViewModel

class PointsDetailsFragment : ViewModelFragment<MineViewModel, FragmentPointsDetailsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPointsDetailsBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    private val mAdapter by lazy { PointsDetailAdapter() }

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = PointsDetailsFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvPoints) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf("", "", ""))
    }
}