package com.xdys.yhyg.entity.goods

data class Evaluate(
    val id: String? = null,
    val userName: String? = null,
    val time: String? = null,
    val specification: String? = null,
    val content: String? = null,
    val portrait: String? = null,
    val rating: String? = null,
    val imgList: MutableList<String> = mutableListOf("", "", "")
)