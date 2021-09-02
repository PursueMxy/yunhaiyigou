package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.adapte.mine.CouponsAdapter
import com.xdys.yhyg.databinding.FragmentCouponsBinding
import com.xdys.yhyg.ui.classify.ShopFragment
import com.xdys.yhyg.vm.MineViewModel

class CouponsFragment : ViewModelFragment<MineViewModel, FragmentCouponsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCouponsBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = CouponsFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    private val mAdapter by lazy { CouponsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvCoupons) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf("1", "2", "3", "4", "5"))
    }
}