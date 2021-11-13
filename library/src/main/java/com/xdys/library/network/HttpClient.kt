package com.xdys.library.network

import com.google.gson.Gson
import com.xdys.library.BuildConfig
import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.convert.CustomConvertFactory
import com.xdys.library.network.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object HttpClient {

    object Url {
        val baseUrl2 = "http://desktop-penm4q0:8000/"

        val baseUrl1 = "https://www.dkgcjy.com/"

        val baseUrl = "https://www.dkgcjy.com/"
//
        

//        val baseUrl = "http://desktop-r37dbo8:8081/"

    }

    private val requestClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
        builder.callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor())
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }
        builder.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Url.baseUrl).client(requestClient)
            .addConverterFactory(CustomConvertFactory(Gson()))
            .build()
    }

    fun <T : BaseApi> create(clazz: Class<T>): T = retrofit.create(clazz)


    private val retrofit2: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Url.baseUrl1).client(requestClient)
            .addConverterFactory(CustomConvertFactory(Gson()))
            .build()
    }

    fun <T : BaseApi> create2(clazz: Class<T>): T = retrofit2.create(clazz)


    private val retrofit3: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Url.baseUrl2).client(requestClient)
            .addConverterFactory(CustomConvertFactory(Gson()))
            .build()
    }

    fun <T : BaseApi> create3(clazz: Class<T>): T = retrofit3.create(clazz)
}