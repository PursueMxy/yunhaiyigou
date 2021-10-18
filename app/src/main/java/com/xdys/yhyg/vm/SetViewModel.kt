package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.xdys.library.base.BaseViewModel
import com.xdys.library.extension.context
import com.xdys.library.network.HttpClient
import com.xdys.yhyg.R
import com.xdys.yhyg.api.LoginApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class SetViewModel : BaseViewModel() {
    private val api by lazy { HttpClient.create(LoginApi::class.java) }
    private val gson by lazy { Gson() }

    var smsCode: String? = ""

    val checkSmsCodeLiveData by lazy { MutableLiveData<Any>() }

    val restLoginPwdLiveData by lazy { MutableLiveData<Any>() }

    /**
     * 检查重置登录的验证码是否正确
     */
    fun checkSmsCode(mobile: String, code: String) {
        val map = hashMapOf("phone" to mobile, "smsCode" to code)
        val body = gson.toJson(map).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch(Dispatchers.IO) {
            fetchEmptyData({ api.checkSmsCode(body) })?.let {
                smsCode = code
                checkSmsCodeLiveData.postValue(it)
            }
        }
    }


    /**
     * 重置密码
     */
    fun restLoginPwd(username: String, smsCode: String, password: String) {
        val map = hashMapOf("phone" to username, "smsCode" to smsCode, "newPassword" to password)
        val body = gson.toJson(map).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch(Dispatchers.IO) {
            fetchData({ api.restLoginPwd(body) })?.let {
                restLoginPwdLiveData.postValue(it)
            }
        }
    }


    /**
     * 退出登录
     */
    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchEmptyData({ api.logout() })?.let {

            }
        }
    }
}