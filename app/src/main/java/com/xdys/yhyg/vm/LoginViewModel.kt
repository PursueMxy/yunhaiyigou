package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xdys.library.base.BaseViewModel
import com.xdys.library.network.HttpClient
import com.xdys.yhyg.api.LoginApi
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create(LoginApi::class.java) }

    val loginLiveData by lazy { MutableLiveData<Any>() }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            fetchData({ api.login(username, password) })?.let {
                loginLiveData.postValue(it)
            }
        }
    }
}