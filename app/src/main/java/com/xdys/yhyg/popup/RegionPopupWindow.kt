package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import android.view.animation.Animation
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.PopubAddressRegionBinding
import com.xdys.yhyg.entity.address.RegionProvince
import razerdp.basepopup.BasePopupWindow
import razerdp.util.animation.AnimationHelper
import razerdp.util.animation.TranslationConfig

class RegionPopupWindow(
    context: Context, private val onRegionSelect: (Triple<Int, Int, Int>) -> Unit
) : BasePopupWindow(context) {

    private lateinit var binding: PopubAddressRegionBinding
    private lateinit var delegate: WheelRegionDelegate

    init {
        setBackPressEnable(true)
        setOutSideDismiss(false)
        contentView = createPopupById(R.layout.popub_address_region)
    }

    override fun onCreateShowAnimation(): Animation {
        return AnimationHelper.asAnimation().withTranslation(TranslationConfig.FROM_BOTTOM).toShow()
    }

    override fun onCreateDismissAnimation(): Animation {
        return AnimationHelper.asAnimation().withTranslation(TranslationConfig.TO_BOTTOM)
            .toDismiss()
    }


    override fun onViewCreated(contentView: View) {
        binding = PopubAddressRegionBinding.bind(contentView)
        delegate = WheelRegionDelegate(
            binding.wheelProvince, binding.wheelCity, binding.wheelDistrict
        )
        binding.tvConfirm.setOnClickListener {
            onRegionSelect(delegate.getSelectedRegionPosition())
            dismiss()
        }
        binding.tvCancel.setOnClickListener { dismiss() }
    }


    fun setInitialData(province: String?, city: String?, district: String?) = apply {
        delegate.setInitialPosition(province, city, district)
    }

    fun setData(list: List<RegionProvince>) = apply {
        delegate.setData(list)
    }
}
