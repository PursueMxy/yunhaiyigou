package com.xdys.yhyg.ui.mall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xdys.yhyg.adapte.mall.MallAdapter
import com.xdys.yhyg.databinding.FragmentMallSecondaryBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class MallSecondaryFragment : ViewModelFragment<MineViewModel, FragmentMallSecondaryBinding>() {

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = MallSecondaryFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMallSecondaryBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    private val mallAdapter by lazy { MallAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvMall) {
            adapter = mallAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun initData() {
        mallAdapter.setNewInstance(mutableListOf("", "", "", ""))
    }
}