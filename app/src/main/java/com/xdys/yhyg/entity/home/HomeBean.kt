package com.xdys.yhyg.entity.home

data class HomeBean(
    var id: String? = null,
    val carouselList: MutableList<BannerBean> = mutableListOf(),
    val goodsCategoryListFirst: MutableList<HomeClassifyBean> = mutableListOf()
)


data class HomeClassifyBean(
    val id: String? = null,
    val name: String? = null,
    val enable: String? = null,
    val tenantId: String? = null,
    val parentId: String? = null,
)