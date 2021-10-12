package com.xdys.library.network.convert

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.xdys.library.network.HttpStatusException
import com.xdys.library.network.base.BaseResult
import okhttp3.ResponseBody
import retrofit2.Converter

class CustomResponseConvert<T>(
    private val gson: Gson, private val adapter: TypeAdapter<T>
) : Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T? {
        return value.use {
            val json = value.string()
            val data = gson.fromJson(json, BaseResult::class.java)
            if (!data.success) {
//                EventBus.getDefault().post(data.code == 6002)
                throw HttpStatusException(data.code, data.msg)
            } else {
                adapter.fromJson(json)
            }
        }
    }
}