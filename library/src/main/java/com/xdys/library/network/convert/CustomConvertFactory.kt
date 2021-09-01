package com.xdys.library.network.convert

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xdys.library.base.CustomRequestConvert
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class CustomConvertFactory (private val gson: Gson) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type, annotations: Array<Annotation>, retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return CustomResponseConvert(gson, gson.getAdapter(TypeToken.get(type)))
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        return CustomRequestConvert(gson, gson.getAdapter(TypeToken.get(type)))
    }
}