package com.xdys.library.config

import com.tencent.mmkv.MMKV
import com.xdys.library.extension.context
import com.xdys.library.network.HttpClient
import java.io.File

object Constant {
    private val tokenKey = "Token"
    private val FIRST = "first"
    val webUrl = "http://192.168.2.2:8080"

    object Key {
        val EXTRA_ID = "id"
        val EXTRA_DATA = "data"
        val EXTRA_TITLE = "title"
        val EXTRA_INDEX = "index"
    }

    object Url {
        val TIPS = HttpClient.Url.baseUrl.plus("")
        val AGREEMENT = HttpClient.Url.baseUrl.plus("")
        val LICENSE = HttpClient.Url.baseUrl.plus("")
        val PRIVACY = HttpClient.Url.baseUrl.plus("")
    }

    object Config {
        val SIZE = 1
        val WX_APPID = "wxef9d34097c2fe25f"
        val compressDir: File
            get() {
                val dir = File(context.filesDir, "compress")
                if (!dir.exists() || dir.isDirectory) dir.mkdir()
                return dir
            }
    }

    var isFirst: Boolean
        get() = MMKV.defaultMMKV().decodeBool(FIRST, true)
        set(value) {
            MMKV.defaultMMKV().encode(FIRST, value)
        }

    var mobile: String?
        get() = MMKV.defaultMMKV().decodeString("mobile")
        set(value) {
            MMKV.defaultMMKV().encode("mobile", value)
        }


    /**
     * 获取保存在本地的用户token
     */
    fun getUserToken(): String? {
        return MMKV.defaultMMKV()?.decodeString(tokenKey)
    }

    /**
     * 保存用户token到本地
     */
    fun saveUserToken(token: String?) = MMKV.defaultMMKV()?.also {
        if (!token.isNullOrBlank()) it.encode(tokenKey, token)
        else if (it.containsKey(tokenKey)) MMKV.defaultMMKV()?.removeValueForKey(tokenKey)
    }
}