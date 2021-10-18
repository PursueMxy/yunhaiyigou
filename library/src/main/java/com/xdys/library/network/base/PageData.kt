package com.xdys.library.network.base

data class PageData<T>(
    var records: MutableList<T> = mutableListOf(),
    var total: Int = 0,
    var pages: Int = 1,
    var size: Int = 10,
)