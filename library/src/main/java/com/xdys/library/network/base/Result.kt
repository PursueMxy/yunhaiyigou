package com.xdys.library.network.base

data class Result<T>(
    val data: T? = null
) : BaseResult()