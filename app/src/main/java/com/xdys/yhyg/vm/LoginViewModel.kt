package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.xdys.library.base.BaseViewModel
import com.xdys.library.extension.context
import com.xdys.library.network.HttpClient
import com.xdys.yhyg.R
import com.xdys.yhyg.api.LoginApi
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class LoginViewModel : BaseViewModel() {

    private val api by lazy { HttpClient.create(LoginApi::class.java) }

    val loginLiveData by lazy { MutableLiveData<Any>() }
    private val gson by lazy { Gson() }
    fun login(username: String, password: String) {
        val map = hashMapOf("username" to username, "password" to password)
        val body = gson.toJson(map).toRequestBody(
            context.getString(R.string.content_type_json).toMediaType()
        )
        viewModelScope.launch {
            fetchData({ api.login(body) })?.let {
                loginLiveData.postValue(it)
            }
        }
    }
}