package com.xdys.yhyg.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.adapte.sale.AfterApplicationAdapter
import com.xdys.yhyg.databinding.FragmentAfterApplicationBinding
import com.xdys.yhyg.entity.sale.AfterApplicationEntity
import com.xdys.yhyg.vm.AfterSaleViewModel

class AfterApplicationFragment :
    ViewModelFragment<AfterSaleViewModel, FragmentAfterApplicationBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAfterApplicationBinding.inflate(inflater, container, false)

    override val viewModel: AfterSaleViewModel by activityViewModels()

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = AfterApplicationFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    private val mAdapter by lazy { AfterApplicationAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvAfter) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(
            mutableListOf(
                AfterApplicationEntity(),
                AfterApplicationEntity(),
                AfterApplicationEntity()
            )
        )
    }
}