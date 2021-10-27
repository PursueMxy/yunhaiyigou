package com.xdys.yhyg.entity.home

data class SeckillData(
    val records: MutableList<SeckillEntity> = mutableListOf()
)

data class SeckillEntity(
    val seckillId: String? = null,
    val hallDate: String? = null,
    val startHallTime: String? = null,
    val endHallTime: String? = null,
    val seckillGoods: MutableList<SeckillGoods> = mutableListOf()
)

data class SeckillGoods(
    val spuId: String? = null,
    val skuId: String? = null,
    val name: String? = null,
    val seckillPrice: String? = null,
    val seckillNum: String? = null,
    val limitNum: String? = null,
    val picUrl: String? = null,
    val seckillRule: String? = null,
    val shareTitle: String? = null,
)