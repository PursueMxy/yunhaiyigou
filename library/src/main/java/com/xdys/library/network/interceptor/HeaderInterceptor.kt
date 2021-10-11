package com.xdys.library.network.interceptor

import com.xdys.library.config.Constant
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            Constant.token?.let { addHeader("token", it) }
            addHeader("Content-Type", "application/json")
            addHeader("sourceType", "2")
            addHeader("tenant_id", "1")
            addHeader("shop_id", "1")
        }.build()
        return chain.proceed(request)
    }
}