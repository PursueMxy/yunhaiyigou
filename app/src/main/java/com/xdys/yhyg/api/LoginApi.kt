package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi : BaseApi {

    @FormUrlEncoded
    @POST("auth/api/login/app")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Result<Any>
}