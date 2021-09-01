package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.xdys.yhyg.databinding.FragmentAddressEditBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class AddressEditFragment : ViewModelFragment<MineViewModel, FragmentAddressEditBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAddressEditBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvDeliveryTips.text = "云南、青海、西藏、新疆、甘肃、海南、宁夏、港澳台等地区暂不支持发货"
    }
}