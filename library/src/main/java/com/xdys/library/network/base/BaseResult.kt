package com.xdys.library.network.base

open class BaseResult {
    var code: Int = 2000
    var msg: String? = null

    val isSuccess: Boolean
        get() = code == 2000
}