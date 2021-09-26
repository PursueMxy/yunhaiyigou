package com.xdys.yhyg.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.mine.DevicesAdapter
import com.xdys.yhyg.databinding.ActivityEquipmentManagementBinding
import com.xdys.yhyg.vm.MineViewModel

class EquipmentManagementActivity :
    ViewModelActivity<MineViewModel, ActivityEquipmentManagementBinding>() {
    override fun createBinding() = ActivityEquipmentManagementBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, EquipmentManagementActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val devicesAdapter by lazy { DevicesAdapter() }

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvDevices) {
            adapter = devicesAdapter
            devicesAdapter.apply {
                setEmptyView(R.layout.empty_devices)
            }
        }
        tvEquipmentName.text = "小米11 Pro"
        tvEquipmentName.setOnClickListener {
            DeviceDetailActivity.start(this@EquipmentManagementActivity)
        }
    }
    override fun initData() {
        devicesAdapter.setDiffNewData(mutableListOf())
    }
}