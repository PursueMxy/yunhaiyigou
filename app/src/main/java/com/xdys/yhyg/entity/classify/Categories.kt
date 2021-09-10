package com.xdys.yhyg.entity.classify

import com.chad.library.adapter.base.entity.node.BaseNode

data class Categories(
    var id: String? = null
)

data class ExternalClassification(
    var name: String? = null,
    var internalList: MutableList<InternalClassification> = mutableListOf()
) : BaseNode() {
    override val childNode: MutableList<BaseNode>
        get() = internalList as MutableList<BaseNode>
}

data class InternalClassification(
    var name: String? = null,
) : BaseNode() {
    override val childNode: MutableList<BaseNode>? = null
}

data class CategoriesBanner(
    var id: String? = null,//广告id
    var pic: String? = null,
)