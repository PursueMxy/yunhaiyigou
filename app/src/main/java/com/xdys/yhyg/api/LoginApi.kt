package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.login.LoginEntity
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface LoginApi : BaseApi {

    @POST("auth/api/login/app")
    suspend fun login(@Body body: RequestBody): Result<LoginEntity>

    @POST("auth/api/sendSms")
    suspend fun sendRegisterSms(@Body body: RequestBody): Result<Any>

    @POST("auth/api/register/app")
    suspend fun register(@Body body: RequestBody): Result<Any>

    @POST("auth/api/checkSmsCode")
    suspend fun checkSmsCode(@Body body: RequestBody): Result<Any>

    @POST("auth/api/restLoginPwd")
    suspend fun restLoginPwd(@Body body: RequestBody): Result<Any>

    @DELETE("auth/logout")
    suspend fun logout(): Result<Any>
}