package com.xdys.yhyg.adapte.sale

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.ui.sale.ChooseAfterSalesActivity

class AfterApplicationAdapter :
    BaseQuickAdapter<String, GoodsViewHolder>(R.layout.item_after_apply) {
    override fun convert(holder: GoodsViewHolder, item: String) {
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