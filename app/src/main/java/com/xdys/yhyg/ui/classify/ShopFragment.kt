package com.xdys.yhyg.ui.classify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.xdys.yhyg.adapte.classify.ShopAdapter
import com.xdys.yhyg.databinding.FragmentShopBinding
import com.xdys.yhyg.vm.CateViewModel
import com.xdys.library.base.ViewModelFragment

class ShopFragment : ViewModelFragment<CateViewModel, FragmentShopBinding>() {

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = ShopFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentShopBinding.inflate(inflater, container, false)

    override val viewModel: CateViewModel by viewModels()

    private val shopAdapter by lazy { ShopAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvShop) {
            adapter = shopAdapter
        }
    }

    override fun initData() {
        shopAdapter.setNewInstance(mutableListOf("", "", "", ""))
    }
}