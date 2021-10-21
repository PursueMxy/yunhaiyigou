package com.xdys.yhyg.popup

import android.util.Log
import com.xdys.library.widget.wheel.view.WheelView
import com.xdys.yhyg.adapte.address.RegionAdapter
import com.xdys.yhyg.entity.address.RegionProvince

class WheelRegionDelegate(
    private val wheelProvince: WheelView,
    private val wheelCity: WheelView,
    private val wheelDistrict: WheelView
) {

    private var list: List<RegionProvince> = mutableListOf()

    private val provinceAdapter = RegionAdapter(mutableListOf())
    private val cityAdapter = RegionAdapter(mutableListOf())
    private val districtAdapter = RegionAdapter(mutableListOf())

    init {
        wheelProvince.adapter = provinceAdapter
        wheelProvince.setItemsVisibleCount(5)
        wheelCity.adapter = cityAdapter
        wheelCity.setItemsVisibleCount(5)
        wheelDistrict.adapter = districtAdapter
        wheelDistrict.setItemsVisibleCount(5)
        // 刷新市区
        wheelProvince.setOnItemSelectedListener { updateCity(it) }
        // 刷新区
        wheelCity.setOnItemSelectedListener { updateDistrict(wheelProvince.currentItem, it) }
    }

    private fun updateCity(provincePosition: Int) {
        val currentCities = list[provincePosition].cities
        cityAdapter.updateList(currentCities)
        wheelCity.adapter = cityAdapter
        wheelCity.currentItem = 0
        updateDistrict(provincePosition, wheelCity.currentItem)
    }

    private fun updateDistrict(provincePosition: Int, cityPosition: Int) {
        val currentDistricts = list[provincePosition].cities[cityPosition].areas
        districtAdapter.updateList(currentDistricts)
        wheelDistrict.adapter = districtAdapter
        wheelDistrict.currentItem = 0
    }

    private fun findProvincePosition(provinceName: String): Int {
        list.forEachIndexed { index, province ->
            if (province.provinceName == provinceName) return index
        }
        return 0
    }

    private fun findCityPosition(provincePosition: Int, cityName: String): Int {
        list[provincePosition].cities.forEachIndexed { index, city ->
            if (city.cityName == cityName) return index
        }
        return 0
    }

    private fun findDistrictPosition(
        provincePosition: Int, cityPosition: Int, districtName: String
    ): Int {
        list[provincePosition].cities[cityPosition].areas.forEachIndexed { index, district ->
            if (district.areasName == districtName) return index
        }
        return 0
    }

    fun setInitialPosition(province: String?, city: String?, district: String?) {
        val provincePosition =
            if (province.isNullOrBlank()) 0 else findProvincePosition(province)
        val cityPosition =
            if (city.isNullOrBlank()) 0 else findCityPosition(provincePosition, city)
        val districtPosition = if (district.isNullOrBlank()) 0 else
            findDistrictPosition(provincePosition, cityPosition, district)
        wheelProvince.currentItem = provincePosition
        updateCity(provincePosition)
        wheelCity.currentItem = cityPosition
        updateDistrict(provincePosition, cityPosition)
        wheelDistrict.currentItem = districtPosition
    }

    fun getSelectedRegionPosition(): Triple<Int, Int, Int> {
        return Triple(wheelProvince.currentItem, wheelCity.currentItem, wheelDistrict.currentItem)
    }

    fun setData(list: List<RegionProvince>) {
        this.list = list
        if (!list.isNullOrEmpty()) {
            provinceAdapter.updateList(list)
            cityAdapter.updateList(list[0].cities)
            districtAdapter.updateList(list[0].cities[0].areas)
            wheelProvince.adapter = provinceAdapter
            wheelCity.adapter = cityAdapter
            wheelDistrict.adapter = districtAdapter
        }
    }
}