package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hjq.toast.ToastUtils
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.mine.AddressListAdapter
import com.xdys.yhyg.databinding.FragmentAddressListBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.config.Constant
import com.xdys.yhyg.entity.address.AddressEntity
import com.xdys.yhyg.popup.PromptPopupWindow
import com.xdys.yhyg.ui.login.LoginActivity
import com.xdys.yhyg.vm.AddressViewModel

class AddressListFragment : ViewModelFragment<AddressViewModel, FragmentAddressListBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAddressListBinding.inflate(inflater, container, false)

    override val viewModel: AddressViewModel by activityViewModels()

    private val navController by lazy { findNavController() }

    private val addressListAdapter by lazy { AddressListAdapter() }

    var id: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        with(rvAddress) {
            adapter = addressListAdapter
        }
        tvCreate.setOnClickListener {
            navController.navigate(R.id.addressEditFragment)
            viewModel.updateAddress(AddressEntity())
        }
        with(addressListAdapter) {
            setOnItemChildClickListener { _, view, position ->
                when (view.id) {
                    R.id.tvDelete -> {
                        id = data[position].id
                        popupPromptback.setData("确认删除该地址么").showPopupWindow()
                    }
                    R.id.tvRedact -> {
                        navController.navigate(R.id.addressEditFragment)
                        viewModel.updateAddress(data[position])
                    }
                    R.id.tvDefaultAddress -> {
                        val map = hashMapOf(
                            "id" to data[position].id,
                            "hasDefault" to if (data[position].hasDefault == "0") "1" else "0"
                        )
                        viewModel.updateAddress(map)
                    }
                }

            }
        }
        refreshLayout.setOnRefreshListener {
            initData()
        }
    }

    override fun initData() {
        viewModel.addressList()
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.addressListLivaData.observe(this) {
            addressListAdapter.setDiffNewData(it)
            binding.refreshLayout.finishRefresh()
        }
        viewModel.deleteAddressLiveData.observe(this) {
            viewModel.addressList()
        }
        viewModel.saveAddressLiveData.observe(this) {
            viewModel.addressList()
        }
    }


    private val popupPromptback: PromptPopupWindow by lazy {
        PromptPopupWindow(requireContext()) {
            id?.let { viewModel.addressDelete(it) }
        }
    }
}