package com.xdys.yhyg.entity.home

data class FavGoodsEntity(
    val id: String? = null,
    val name: String? = null,
    val picUrls: MutableList<String> = mutableListOf(),
    val priceDown: String? = null
)