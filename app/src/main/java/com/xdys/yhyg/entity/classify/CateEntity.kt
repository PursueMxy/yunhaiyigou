package com.xdys.yhyg.entity.classify

import com.chad.library.adapter.base.entity.node.BaseNode
import java.io.Serializable

data class CateEntity(
    val id: String? = null,
    val parentId: String? = null,
    val sort: String? = null,
    val children: MutableList<Categories>? = null,
    val tenantId: String? = null,
    val enable: String? = null,
    val shopId: String? = null,
    val name: String? = null
) : BaseNode(), Serializable {
    override val childNode: MutableList<BaseNode>?
        get() = children as? MutableList<BaseNode>
}
