package com.xdys.yhyg.entity.classify

data class SortEntity(
    val id: String? = null,
    val name: String? = null,
    val canSort: Boolean = false,
    val sortParam: Int = 999
) {
    var sortAsc: Boolean = false

    val realSort: Int
        get() = if (sortAsc) sortParam else sortParam + 1
}