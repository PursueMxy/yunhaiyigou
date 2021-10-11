package com.xdys.yhyg.entity.home

import com.xdys.yhyg.entity.goods.GoodsEntity

data class CouponCenterEntity(
    val id: String? = null,
    val price: String? = null,
    val goodsList: MutableList<GoodsEntity> = mutableListOf()
)