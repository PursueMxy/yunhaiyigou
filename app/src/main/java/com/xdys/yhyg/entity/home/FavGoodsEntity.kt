package com.xdys.yhyg.entity.home

data class FavGoodsEntity(
    val id: String? = null,
    val name: String? = null,
    val picUrls: MutableList<String> = mutableListOf(),
    val priceDown: String? = null,
    val shopId:String?=null,
    val sellPoint:String?=null,
    val description:String?=null,
    val priceUp:String?=null,
    val saleNum:String?=null,
)

