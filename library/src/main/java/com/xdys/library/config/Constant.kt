package com.xdys.library.config

import com.tencent.mmkv.MMKV
import com.xdys.library.extension.context
import com.xdys.library.network.HttpClient
import java.io.File

object Constant {
    private val TOKEN = "Token"
    private val POSITIONING = "Positioning"//是否开启定位
    private val FIRST = "first"
    private val LOCATION = "location"
    private val HISTORY = "history"
    private val RECIPE = "recipe"

    object Key {
        val EXTRA_ID = "id"
        val EXTRA_DATA = "data"
        val EXTRA_TITLE = "title"
        val EXTRA_INDEX = "index"

    }

    object Url {
        val TIPS = HttpClient.Url.baseUrl.plus("article/tips")
        val AGREEMENT = HttpClient.Url.baseUrl.plus("article/userAgreement")
        val LICENSE = HttpClient.Url.baseUrl.plus("article/license")
        val PRIVACY = HttpClient.Url.baseUrl.plus("article/privacy")
    }

    object Config {
        val SIZE = 20
        val WX_APPID = "wx340fb564c21261de"

        //        val WX_APPID = "wx86beac0dd2d8ebe2"
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
    var isPositioning: Boolean
        get() = MMKV.defaultMMKV().decodeBool(POSITIONING, true)
        set(value) {
            MMKV.defaultMMKV().encode(POSITIONING, value)
        }
    var selectedLocation: String?
        get() = MMKV.defaultMMKV().decodeString(LOCATION)
        set(value) {
            MMKV.defaultMMKV().encode(LOCATION, value)
        }
    var token: String?
        get() = MMKV.defaultMMKV().decodeString(TOKEN)
        set(value) {
            MMKV.defaultMMKV().encode(TOKEN, value)
        }
    var searchHistory: MutableSet<String>?
        get() = MMKV.defaultMMKV().decodeStringSet(HISTORY)
        set(value) {
            if (value == null) MMKV.defaultMMKV().encode(HISTORY, emptySet())
            else MMKV.defaultMMKV().encode(HISTORY, value)
        }
    var searchRecipeHistory: MutableSet<String>?
        get() = MMKV.defaultMMKV().decodeStringSet(RECIPE)
        set(value) {
            if (value == null) MMKV.defaultMMKV().encode(RECIPE, emptySet())
            else MMKV.defaultMMKV().encode(RECIPE, value)
        }
}