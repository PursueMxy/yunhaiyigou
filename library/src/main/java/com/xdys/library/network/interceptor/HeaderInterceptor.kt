package com.xdys.library.network.interceptor

import com.xdys.library.config.Constant
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            Constant.getUserToken()?.let { addHeader("Authorization", "Bearer $it") }
            addHeader("sourcetype", "2")
            addHeader("tenantid", "1")
            addHeader("shopid", "1")
            addHeader("Content-Type", "application/json;charset=utf-8")
        }.build()
        return chain.proceed(request)
    }
}