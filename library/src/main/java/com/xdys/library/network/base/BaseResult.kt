package com.xdys.library.network.base

open class BaseResult {
    var code: Int = 200
    var msg: String? = null
    fun isSuccess(): Boolean = code == 200

}