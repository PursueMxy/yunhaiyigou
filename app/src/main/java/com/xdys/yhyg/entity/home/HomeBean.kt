package com.xdys.yhyg.entity.home

data class HomeBean(
    var id: String? = null,
    val carouselList: MutableList<BannerBean> = mutableListOf(),
    val goodsCategoryListFirst: MutableList<HomeClassifyBean> = mutableListOf(),
//    val buttonList: MutableList<ButtonList> = mutableListOf(),
    val disImg: DisImg? = null
)


data class HomeClassifyBean(
    val id: String? = null,
    val name: String? = null,
    val enable: String? = null,
    val tenantId: String? = null,
    val parentId: String? = null,
)

data class ButtonList(
    val id: String? = null,
    val icon: Int? = null,
    val text: String? = null,
)

data class DisImg(
    val imgUrl: String? = null,
    val url: String? = null,
)