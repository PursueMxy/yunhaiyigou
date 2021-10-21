package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.xdys.library.base.BaseViewModel
import com.xdys.library.event.DisposableLiveData
import com.xdys.library.extension.context
import com.xdys.library.network.HttpClient
import com.xdys.yhyg.R
import com.xdys.yhyg.api.AddressApi
import com.xdys.yhyg.entity.address.AddressEntity
import com.xdys.yhyg.entity.address.RegionProvince
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.BufferedReader
import java.io.InputStreamReader

class AddressViewModel : BaseViewModel() {

    private val gson by lazy { Gson() }

    private val api by lazy { HttpClient.create2(AddressApi::class.java) }

    val addressLiveData by lazy { MutableLiveData<AddressEntity>() }

    val regionLiveData by lazy { DisposableLiveData<List<RegionProvince>>() }

    val saveAddressLiveData by lazy { DisposableLiveData<Any>() }

    val deleteAddressLiveData by lazy { DisposableLiveData<Any>() }

    val addressListLivaData by lazy { MutableLiveData<MutableList<AddressEntity>>() }

    val updateAddressLivaData by lazy { MutableLiveData<AddressEntity>() }

    val defaultAddressLivaData by lazy { MutableLiveData<AddressEntity>() }

    /**
     * 更新地址
     */
    fun updateAddress(address: AddressEntity) {
        updateAddressLivaData.value = address
    }

    /**
     * 添加收货地址
     */
    fun saveAddress(map: Map<String, String?>) {
        val body = gson.toJson(map).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch(Dispatchers.IO) {
            fetchEmptyData({ api.saveAddress(body) })?.let {
                saveAddressLiveData.postValue(it)
            }
        }
    }

    /**
     * 添加收货地址
     */
    fun updateAddress(map: Map<String, String?>) {
        val body = gson.toJson(map).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch(Dispatchers.IO) {
            fetchEmptyData({ api.updateAddress(body) })?.let {
                saveAddressLiveData.postValue(it)
            }
        }
    }

    /**
     * 删除收货地址
     */
    fun addressDelete(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchEmptyData({ api.addressDelete(id) })?.let {
                deleteAddressLiveData.postValue(it)
            }
        }
    }

    /**
     * 收货地址列表
     */
    fun addressList() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.addressList() })?.let {
                addressListLivaData.postValue(it)
            }
        }
    }

    /**
     * 默认收货地址
     */
    fun defaultAddress() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.defaultAddress() })?.let {
                defaultAddressLivaData.postValue(it)
            }
        }
    }


    fun parseRegionData() = viewModelScope.launch(Dispatchers.IO) {
        val builder = StringBuilder()
        val bufferedReader = BufferedReader(InputStreamReader(context.assets.open("region.json")))
        var content: String?
        while (bufferedReader.readLine().also { content = it } != null) {
            builder.append(content)
        }
        bufferedReader.close()
        val list = Gson().fromJson(builder.toString(), Array<RegionProvince>::class.java).asList()
        regionLiveData.postValue(list)
    }

    fun setSelectedRegion(provincePosition: Int, cityPosition: Int, districtPosition: Int) {
        val list = regionLiveData.value ?: return
        val address = addressLiveData.value ?: return
        val selectedProvince = list[provincePosition]
        address.province = selectedProvince.provinceCode
        address.provinceName = selectedProvince.provinceName
        val selectedCity = list[provincePosition].cities[cityPosition]
        address.city = selectedCity.cityCode
        address.cityName = selectedCity.cityName
        val selectedDistrict =
            list[provincePosition].cities[cityPosition].areas[districtPosition]
        address.towns = selectedDistrict.areasCode
        address.townsName = selectedDistrict.areasName
        addressLiveData.postValue(address)
    }

}