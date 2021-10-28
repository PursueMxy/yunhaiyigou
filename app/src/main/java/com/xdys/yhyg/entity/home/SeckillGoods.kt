package com.xdys.yhyg.entity.home

data class SeckillData(
    val records: MutableList<SeckillEntity> = mutableListOf()
)

data class SeckillEntity(
    val seckillId: String? = null,
    val hallDate: String? = null,
    val startHallTime: Long? = 0,
    val endHallTime: Long? = 0,
    val seckillGoods: MutableList<SeckillGoods> = mutableListOf()
)

data class SeckillGoods(
    val spuId: String? = null,
    val skuId: String? = null,
    val name: String? = null,
    val seckillPrice: String? = null,
    val seckillNum: Int = 0,
    val limitNum: Int = 0,
    val picUrl: String? = null,
    val seckillRule: String? = null,
    val shareTitle: String? = null,
    val salesPrice: String? = null,
    val shopId: String? = null,
    val shopName: String? = null
)