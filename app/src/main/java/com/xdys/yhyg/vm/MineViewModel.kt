package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.xdys.library.base.BaseViewModel
import com.xdys.library.extension.context
import com.xdys.library.network.HttpClient
import com.xdys.yhyg.R
import com.xdys.yhyg.api.MineApi
import com.xdys.yhyg.entity.mine.CollectEntity
import com.xdys.yhyg.entity.mine.UserInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class MineViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create2(MineApi::class.java) }
    val userInfoLivaData by lazy { MutableLiveData<UserInfoEntity>() }
    val collectListLivaData by lazy { MutableLiveData<MutableList<CollectEntity>>() }
    val updateEditLiveData by lazy {  MutableLiveData<Boolean>() }

    val updateCollectLiveData by lazy {  MutableLiveData<Boolean>() }

    private val gson by lazy { Gson() }
    //用户信息
    fun userInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.userInfo() })?.let {
                userInfoLivaData.postValue(it)
            }
        }
    }

    /**
     * 收藏列表
     */
    fun collectList() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.collectList() })?.let {
                collectListLivaData.postValue(it)
            }
        }
    }

    //收藏页编辑
    fun updateEdit(update: Boolean) {
        updateEditLiveData.postValue(update)
    }


    /**
     * 新增用户收藏
     */
    fun collect(relationId: String) {
        viewModelScope.launch {
            fetchData({ api.collect(relationId) })?.let {
                updateCollectLiveData.postValue(true)
            }
        }
    }
}