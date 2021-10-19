package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xdys.library.base.BaseViewModel
import com.xdys.library.network.HttpClient
import com.xdys.yhyg.api.MineApi
import com.xdys.yhyg.entity.mine.UserInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MineViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create(MineApi::class.java) }
    val userInfoLivaData by lazy { MutableLiveData<UserInfoEntity>() }

    //用户信息
    fun userInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.userInfo() })?.let {
                userInfoLivaData.postValue(it)
            }
        }
    }
}