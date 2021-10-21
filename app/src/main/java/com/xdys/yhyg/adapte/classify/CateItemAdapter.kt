package com.xdys.yhyg.adapte.classify

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.home.GoodsTypeAdapter
import com.xdys.yhyg.ui.classify.SingleCategoryActivity
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.entity.cart.CartGoods

class CateItemAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_cate_list) {

    private val goodsTypeAdapter by lazy { GoodsTypeAdapter() }


    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvClassifyName, item)
//        goodsTypeAdapter.setNewInstance(mutableListOf("夏上新", "JK制服", "连衣裙", "卫衣"))
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        with(viewHolder.getView<RecyclerView>(R.id.rvClassify)) {
            adapter = goodsTypeAdapter
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(DividerItemDecoration(8.px, 0.px))
        }
        with(goodsTypeAdapter) {
            setOnItemClickListener { _, view, position ->
                SingleCategoryActivity.start(context,"","")
            }
        }
    }
}

