package com.xdys.yhyg.entity.address

import com.google.gson.annotations.SerializedName
import com.xdys.library.widget.wheel.interf.IPickerViewData

data class RegionProvince(
    val provinceCode: String,
    val provinceName: String,
    @SerializedName("citys")
    val cities: MutableList<RegionCity> = mutableListOf()
) : IPickerViewData {
    override fun getPickerViewText(): String = provinceName
}

data class RegionCity(
    val cityCode: String,
    val cityName: String,
    val areas: MutableList<RegionDistrict> = mutableListOf()
) : IPickerViewData {
    override fun getPickerViewText(): String = cityName
}

data class RegionDistrict(
    val areasCode: String,
    val areasName: String
) : IPickerViewData {
    override fun getPickerViewText(): String = areasName
}