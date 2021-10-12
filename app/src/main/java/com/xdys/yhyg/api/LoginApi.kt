package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.login.LoginEntity
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi : BaseApi {

    @POST("login/app")
    suspend fun login(@Body body: RequestBody): Result<LoginEntity>
}