package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.classify.CateEntity
import retrofit2.http.GET

interface ClassificationApi : BaseApi {

    @GET("/mall-goods/api/goodsSpu/page")
    suspend fun goodsSpu(): Result<Any>

    @GET("/mall-goods/api/goodscatshop/tree")
    suspend fun goodsClassify(): Result<MutableList<CateEntity>>

}