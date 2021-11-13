package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.login.LoginEntity
import com.xdys.yhyg.entity.mine.CollectEntity
import com.xdys.yhyg.entity.mine.UserInfoEntity
import okhttp3.RequestBody
import retrofit2.http.*

interface MineApi : BaseApi {

    @GET("/mall-user/api/user/info")
    suspend fun userInfo(): Result<UserInfoEntity>

    @GET("/mall-user/api/collect/list")
    suspend fun collectList(): Result<MutableList<CollectEntity>>

    @DELETE("/mall-user/api/collect/{ids}")
    suspend fun collect(@Path("ids") id:String): Result<Any>
}