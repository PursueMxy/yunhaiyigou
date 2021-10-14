package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.xdys.library.base.BaseViewModel
import com.xdys.library.config.Constant
import com.xdys.library.extension.context
import com.xdys.library.network.HttpClient
import com.xdys.yhyg.R
import com.xdys.yhyg.api.LoginApi
import com.xdys.yhyg.entity.login.LoginEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class LoginViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create(LoginApi::class.java) }
    private val gson by lazy { Gson() }

    val loginLiveData by lazy { MutableLiveData<LoginEntity>() }

    val registerLiveData by lazy { MutableLiveData<Any>() }

    fun login(username: String, password: String) {
        val map = hashMapOf("username" to username, "password" to password)
        val body = gson.toJson(map).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch {
            fetchData({ api.login(body) })?.let {
                Constant.saveUserToken(it.access_token)
                Constant.mobile = username
                loginLiveData.postValue(it)
            }
        }
    }

    fun sendRegisterSms(mobile: String, type: String) {
        val map = hashMapOf("phone" to mobile, "type" to type)
        val body = gson.toJson(map).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch(Dispatchers.IO) {
            fetchEmptyData({ api.sendRegisterSms(body) })?.let {
                countdown(60)
            }
        }
    }

    fun register(username: String, smsCode: String, password: String) {
        val map = hashMapOf("username" to username, "smsCode" to smsCode, "password" to password)
        val body = gson.toJson(map).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.register(body) })?.let {
                registerLiveData.postValue(it)
            }
        }
    }


}