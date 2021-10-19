package com.xdys.yhyg.adapte.home

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.goods.ProductType

class ProductTypeAdapter :
    BaseQuickAdapter<ProductType, ProductTypeViewHolder>(R.layout.item_product_type) {

    init {
        setDiffCallback(ProductTypeDiffCallback())
    }

    override fun convert(holder: ProductTypeViewHolder, item: ProductType) {
        holder.setText(R.id.tvTitle, item.title)
//        holder.mAdapter.setNewInstance(item.typeList)
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


class ProductTypeDiffCallback : DiffUtil.ItemCallback<ProductType>() {
    override fun areItemsTheSame(
        oldItem: ProductType,
        newItem: ProductType
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ProductType,
        newItem: ProductType
    ): Boolean {
        return oldItem.title == newItem.title
    }
}