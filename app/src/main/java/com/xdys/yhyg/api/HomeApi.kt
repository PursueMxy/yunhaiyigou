package com.xdys.yhyg.api

import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.PageData
import com.xdys.library.network.base.Result
import com.xdys.yhyg.entity.home.BannerBean
import com.xdys.yhyg.entity.home.HomeBean
import com.xdys.yhyg.entity.home.HomeClassifyBean
import retrofit2.http.GET

interface HomeApi : BaseApi {


    @GET("/mall-goods/api/homecat")
    suspend fun goodsPage(): Result<HomeBean>

}