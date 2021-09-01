package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.xdys.yhyg.adapte.mine.ShopCollectionAdapter
import com.xdys.yhyg.databinding.FragmentShopCollectionBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class ShopCollectionFragment : ViewModelFragment<MineViewModel, FragmentShopCollectionBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentShopCollectionBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = ShopCollectionFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    private val mAdapter by lazy { ShopCollectionAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvShop) {
            adapter = mAdapter
        }
    }

    override fun initData() {
        mAdapter.setNewInstance(mutableListOf("", "", ""))
    }
}