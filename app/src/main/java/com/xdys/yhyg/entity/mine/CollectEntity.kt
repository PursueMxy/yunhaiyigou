package com.xdys.yhyg.entity.mine

data class CollectEntity(
    val id: String? = null,
    val type: String? = null,
    val relationId: String? = null,
    val goodsSpuApiVo: GoodsSpu? = null
){
    var selected = false
}

data class GoodsSpu(
    val id: String? = null,
    val spuCode: String? = null,
    val name: String? = null,
    val picUrls: String? = null,
    val priceDown: String? = null,
    val priceUp: String? = null,
    val shelf: String? = null,
    val freightTemplatId: String? = null,
    val verifyStatus: String? = null,
    val pointsGiveSwitch: String? = null,
    val pointsGiveNum: String? = null,
    val pointsDeductSwitch: String? = null,
    val pointsDeductScale: String? = null,
    val pointsDeductAmount: String? = null,
)