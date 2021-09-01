package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.mine.AddressListAdapter
import com.xdys.yhyg.databinding.FragmentAddressListBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class AddressListFragment : ViewModelFragment<MineViewModel, FragmentAddressListBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAddressListBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    private val navController by lazy { findNavController() }

    private val addressListAdapter by lazy { AddressListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvAddress) {
            adapter = addressListAdapter
        }
        tvCreate.setOnClickListener {
            navController.navigate(R.id.addressEditFragment)
        }
    }

    override fun initData() {
        addressListAdapter.setNewInstance(mutableListOf("", "", ""))
    }
}