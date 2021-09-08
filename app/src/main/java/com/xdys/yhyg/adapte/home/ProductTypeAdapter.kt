package com.xdys.yhyg.adapte.home

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.R

class ProductTypeAdapter :
    BaseQuickAdapter<String, ProductTypeViewHolder>(R.layout.item_product_type) {
    override fun convert(holder: ProductTypeViewHolder, item: String) {
        holder.setText(R.id.tvTitle, "女式上衣")
        holder.mAdapter.setNewInstance(
            mutableListOf("T桖", "Polo衫", "拼接衬衫", "卫衣", "小西服", "衬衫", "卫衣")
        )
    }

    override fun onItemViewHolderCreated(viewHolder: ProductTypeViewHolder, viewType: Int) {
        with(viewHolder.getView<RecyclerView>(R.id.rvGoodsType)) {
            adapter = viewHolder.mAdapter
            layoutManager = GridLayoutManager(context, 4)
            addItemDecoration(DividerItemDecoration(31.px, 15.px))
        }
    }

}

class ProductTypeViewHolder(view: View) : BaseViewHolder(view) {
    val mAdapter by lazy { GoodsTypeAdapter() }
}