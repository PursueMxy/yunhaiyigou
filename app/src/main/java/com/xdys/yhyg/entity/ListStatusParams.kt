package com.xdys.yhyg.entity

import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.xdys.library.config.Constant

data class ListStatusParams(
    var fetchSuccess: Boolean = true,
    var type: TYPE = TYPE.RESTART,
    var hasMore: Boolean = true,
    var size: Int = 0
) {
    enum class TYPE {
        RESTART, LOAD_MORE
    }

    fun failure(page: Int): ListStatusParams {
        fetchSuccess = false
        type = if (page == 1) TYPE.RESTART else TYPE.LOAD_MORE
        hasMore = false
        size = 0
        return this
    }

    fun success(page: Int, size: Int): ListStatusParams {
        fetchSuccess = true
        type = if (page == 1) TYPE.RESTART else TYPE.LOAD_MORE
        hasMore = size >= Constant.Config.SIZE
        this.size = size
        return this
    }

    fun restoreView(refreshLayout: SmartRefreshLayout?, adapter: BaseQuickAdapter<*, *>?) {
        when (type) {
            TYPE.RESTART -> {
                refreshLayout?.finishRefresh(fetchSuccess)
                if (size < Constant.Config.SIZE) adapter?.loadMoreModule?.loadMoreEnd(true)
            }
            TYPE.LOAD_MORE -> adapter?.loadMoreModule?.let {
                if (!fetchSuccess) it.loadMoreFail()
                else if (size < Constant.Config.SIZE) it.loadMoreEnd(false)
                else it.loadMoreComplete()
            }
        }
    }
}