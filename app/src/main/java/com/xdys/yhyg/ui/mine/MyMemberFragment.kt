package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.adapte.mine.MyMemberAdapter
import com.xdys.yhyg.databinding.FragmentMyMemberBinding
import com.xdys.yhyg.vm.MineViewModel

class MyMemberFragment : ViewModelFragment<MineViewModel, FragmentMyMemberBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMyMemberBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = MyMemberFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    private val mAdapter by lazy { MyMemberAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvMyMember) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setDiffNewData(mutableListOf())
    }
}