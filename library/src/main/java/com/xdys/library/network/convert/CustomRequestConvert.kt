package com.xdys.library.base

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.xdys.library.R
import com.xdys.library.extension.context
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

class CustomRequestConvert<T>(
    private val gson: Gson, private val adapter: TypeAdapter<T>
) : Converter<T, RequestBody> {
    private val MEDIA_TYPE = context.getString(R.string.content_type_json).toMediaType()
    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        return gson.newJsonWriter(
            OutputStreamWriter(Buffer().outputStream(), StandardCharsets.UTF_8)
        ).use {
            adapter.write(it, value)
            buffer
        }.readByteString().toRequestBody(MEDIA_TYPE)
    }
}