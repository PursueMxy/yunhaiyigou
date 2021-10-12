package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import okhttp3.RequestBody
import retrofit2.http.*

interface LoginApi : BaseApi {

    @POST("login/app")
    suspend fun login(@Body body: RequestBody): Result<Any>
}