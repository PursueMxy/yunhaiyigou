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


data class GoodsDetailEntity(
    val delFlag: String? = null,
    val createUserId: String? = null,
    val createTime: String? = null,
    val updateUserId: String? = null,
    val updateTime: String? = null,
    val tenantId: String? = null,
    val id: String? = null,
    val shopId: String? = null,
    val spuCode: String? = null,
    val name: String? = null,
    val sellPoint: String? = null,
    val description: String? = null,
    val categoryFirst: String? = null,
    val categorySecond: String? = null,
    val categoryShopFirst: String? = null,
    val categoryShopSecond: String? = null,
    val picUrls: MutableList<String> = mutableListOf(),
    val shelf: String? = null,
    val sort: String? = null,
    val priceDown: String? = null,
    val priceUp: String? = null,
    val saleNum: String? = null,
    val specType: String? = null,
    val pointsGiveSwitch: String? = null,
    val pointsGiveNum: String? = null,
    val pointsDeductSwitch: String? = null,
    val pointsDeductScale: String? = null,
    val pointsDeductAmount: String? = null,
    val freightTemplatId: String? = null,
    val verifyStatus: String? = null,
    val verifyDetail: String? = null,
    val isHot: String? = null,
    val skus: MutableList<skuEntity> = mutableListOf()


)

data class skuEntity(
    val delFlag: String? = null,
    val createUserId: String? = null,
    val createTime: String? = null,
    val updateUserId: String? = null,
    val updateTime: String? = null,
    val tenantId: String? = null,
    val id: String? = null,
    val shopId: String? = null,
    val skuCode: String? = null,
    val spuId: String? = null,
    val specValueName: String? = null,
    val specs: MutableList<specs> = mutableListOf()
)

data class specs(
    val delFlag: String? = null,
    val specValueName: String? = null,
    val specId: String? = null,
    val selected: Boolean = false
)