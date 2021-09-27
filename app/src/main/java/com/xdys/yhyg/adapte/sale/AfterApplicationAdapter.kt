package com.xdys.yhyg.adapte.sale

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.sale.AfterApplicationEntity
import com.xdys.yhyg.ui.sale.ChooseAfterSalesActivity

class AfterApplicationAdapter :
    BaseQuickAdapter<AfterApplicationEntity, GoodsViewHolder>(R.layout.item_after_apply) {

    init {
        setDiffCallback(AfterApplicationDiffCallback())
    }

    override fun convert(holder: GoodsViewHolder, item: AfterApplicationEntity) {
        holder.setText(R.id.tvShopName, "杜康新能源商城")
        holder.goodsAdapter.setNewInstance(mutableListOf("", ""))
        holder.goodsAdapter.setOnItemClickListener { _, _, position ->
            ChooseAfterSalesActivity.start(context)
        }
    }

    override fun onItemViewHolderCreated(viewHolder: GoodsViewHolder, viewType: Int) {
        with(viewHolder.getView<RecyclerView>(R.id.rvGoods)) {
            adapter = viewHolder.goodsAdapter
        }
    }

}

class GoodsViewHolder(view: View) : BaseViewHolder(view) {
    val goodsAdapter = ReturnGoodsAdapter()
}

class AfterApplicationDiffCallback : DiffUtil.ItemCallback<AfterApplicationEntity>() {
    override fun areItemsTheSame(
        oldItem: AfterApplicationEntity,
        newItem: AfterApplicationEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AfterApplicationEntity,
        newItem: AfterApplicationEntity
    ): Boolean {
        return oldItem.shopName == newItem.shopName
    }
}