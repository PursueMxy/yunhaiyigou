package com.xdys.yhyg.ui.mall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.adapte.mall.PhysicalStoreAdapter
import com.xdys.yhyg.databinding.FragmentEntityBinding
import com.xdys.yhyg.entity.mall.PhysicalStore
import com.xdys.yhyg.vm.MallViewModel

class EntityFragment : ViewModelFragment<MallViewModel, FragmentEntityBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentEntityBinding.inflate(inflater, container, false)

    override val viewModel: MallViewModel by viewModels()

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = EntityFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    private val physicalStoreAdapter by lazy { PhysicalStoreAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvShop) {
            adapter = physicalStoreAdapter
        }
        with(physicalStoreAdapter) {
            setOnItemClickListener { adapter, view, position ->
                BusinessDetailsActivity.start(requireContext())
            }
        }

    }

    override fun initData() {
        physicalStoreAdapter.setDiffNewData(mutableListOf(PhysicalStore(),PhysicalStore()))
    }
}