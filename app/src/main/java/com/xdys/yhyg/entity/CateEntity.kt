package com.xdys.yhyg.entity

import com.chad.library.adapter.base.entity.node.BaseNode
import java.io.Serializable

data class CateEntity(
    val category_id: String? = null,
    val category_name: String? = null,
    val category_pic: String? = null,
    val child_list: MutableList<CateEntity>? = null
) : BaseNode(), Serializable {
    override val childNode: MutableList<BaseNode>?
        get() = child_list as? MutableList<BaseNode>
}
