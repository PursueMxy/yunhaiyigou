package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hjq.toast.ToastUtils
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentAddressEditBinding
import com.xdys.yhyg.entity.address.AddressEntity
import com.xdys.yhyg.popup.RegionPopupWindow
import com.xdys.yhyg.vm.AddressViewModel

class AddressEditFragment : ViewModelFragment<AddressViewModel, FragmentAddressEditBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAddressEditBinding.inflate(inflater, container, false)

    override val viewModel: AddressViewModel by activityViewModels()

    private val navController by lazy { findNavController() }

    private val regionPopupWindow: RegionPopupWindow by lazy {
        RegionPopupWindow(requireContext()) {
            viewModel.setSelectedRegion(it.first, it.second, it.third)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvDeliveryTips.text = "云南、青海、西藏、新疆、甘肃、海南、宁夏、港澳台等地区暂不支持发货"
        ivSetDefaultAddress.setOnClickListener {
            it.isSelected = !it.isSelected
        }
        etCity.setOnClickListener {
            val list = viewModel.regionLiveData.value
            if (list == null) viewModel.parseRegionData() else {
                regionPopupWindow.setData(list)
                    .showPopupWindow()
            }
        }
        tvSaveAddress.setOnClickListener {
            savaAddress()
        }
    }


    fun savaAddress() {
        with(binding) {
            val consigneeName = etConsignee.text.toString()
            val phone = etPhoneNumber.text.toString()
            val detailedAddress = etDetailedAddress.text.toString()
            if (consigneeName.isEmpty()) {
                ToastUtils.show(etConsignee.hint.toString())
                return
            }
            if (phone.isEmpty()) {
                ToastUtils.show(etPhoneNumber.hint.toString())
                return
            }

            if (viewModel.addressLiveData.value?.towns == null) {
                ToastUtils.show("省市区不能为空")
                return
            }
            if (detailedAddress.isEmpty()) {
                ToastUtils.show(etDetailedAddress.hint.toString())
                return
            }
            viewModel.addressLiveData.value?.let {
                val map = hashMapOf(
                    "consigneeName" to consigneeName,
                    "phone" to phone,
                    "detailedAddress" to detailedAddress,
                    "province" to it.province,
                    "city" to it.city,
                    "towns" to it.towns,
                    "provinceName" to it.provinceName,
                    "cityName" to it.cityName,
                    "townsName" to it.townsName,
                    "hasDefault" to if (ivSetDefaultAddress.isSelected) "1" else "0"
                )
                if (viewModel.updateAddressLivaData.value?.id != null) {
                    map["id"] = viewModel.updateAddressLivaData.value?.id
                    viewModel.updateAddress(map)
                } else {
                    viewModel.saveAddress(map)
                }
            }
        }

    }


    override fun initData() {
        viewModel.parseRegionData()
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.addressLiveData.observe(this) {
            binding.etCity.text =
                (it.provinceName ?: "").plus(it.cityName ?: "").plus(it.townsName ?: "")

        }
        viewModel.saveAddressLiveData.observe(this) {
            navController.navigate(R.id.addressListFragment)
        }
        viewModel.updateAddressLivaData.observe(this) {
            viewModel.addressLiveData.value = it
            binding.etDetailedAddress.setText(it.detailedAddress)
            binding.etPhoneNumber.setText(it.phone)
            binding.etConsignee.setText(it.consigneeName)
            binding.etCity.text = "${it.provinceName}${it.cityName}${it.townsName}"
            binding.ivSetDefaultAddress.isSelected = it.hasDefault == "1"
        }
    }
}