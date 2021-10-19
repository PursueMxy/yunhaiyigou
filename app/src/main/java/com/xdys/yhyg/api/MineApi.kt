package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.login.LoginEntity
import com.xdys.yhyg.entity.mine.UserInfoEntity
import retrofit2.http.GET

interface MineApi : BaseApi {

    @GET("/mall-user/api/user/info")
    suspend fun userInfo(): Result<UserInfoEntity>
}