package com.xdys.yhyg.adapte.mall

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.home.GoodsTypeAdapter
import com.xdys.yhyg.ui.mall.EntityListActivity
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration

class MallCateAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.mall_cate_item) {

    private val goodsTypeAdapter by lazy { GoodsTypeAdapter() }

    override fun convert(holder: BaseViewHolder, item: String) {
        goodsTypeAdapter.setNewInstance(
            mutableListOf(
                "超市", "生鲜果蔬", "乳制品", "休闲零食",
                "粮油副食", "个人洗护", "酒水饮料"
            )
        )
        goodsTypeAdapter.setOnItemClickListener { adapter, view, position ->
            EntityListActivity.start(context)
        }
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        with(viewHolder.getView<RecyclerView>(R.id.rvCate)) {
            adapter = goodsTypeAdapter
            layoutManager = GridLayoutManager(context, 5)
            addItemDecoration(DividerItemDecoration(8.px, 17.px))
        }
    }
}
