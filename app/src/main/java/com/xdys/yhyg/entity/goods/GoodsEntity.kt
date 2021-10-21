package com.xdys.yhyg.entity.goods

data class GoodsEntity(
    val id: String? = null,
    val goodsName: String? = null,
    val price: String? = null,
    val image: String? = null,
    val sold: String? = null
)

data class ProductType(
    val id: String? = null,
    val title: String? = null,
    val typeList: MutableList<String> = mutableListOf(
        "T桖",
        "Polo衫",
        "拼接衬衫",
        "卫衣",
        "小西服",
        "衬衫",
        "卫衣"
    )
)

data class SecondsKill(
    val id: String? = null,
    val goodsName: String? = null,
    val price: String? = null,
    val originalPrice: String? = null,
    val sold: String? = null,
    val image: String? = null,
)

data class Spike(
    val id: String? = null,
    val goodsName: String? = null,
    val price: String? = null,
    val originalPrice: String? = null,
    val image: String? = null,
)


data class GoodsDetail(
    val delFlag: String? = null,
)