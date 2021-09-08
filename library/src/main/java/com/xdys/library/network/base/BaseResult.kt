package com.xdys.library.network.base

open class BaseResult {
    var code: Int = 1
    var msg: String? = null

    val isSuccess: Boolean
        get() = code == 1
}