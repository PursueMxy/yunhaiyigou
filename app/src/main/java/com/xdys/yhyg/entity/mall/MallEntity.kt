package com.xdys.yhyg.entity.mall

data class MallEntity(
    val id: String? = null,
    val shopName: String? = null,
    val distance: String? = null,
    val openTime: String? = null,
    val address: String? = null,
    val logo: String? = null,
    val labelList: MutableList<String> = mutableListOf()
)

data class PhysicalStore(
    val id:String?=null,
    val supermarket:String?=null,
    val businessHours:String?=null,
    val address: String?=null,

)