package com.xdys.yhyg.adapte.order

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R

class OrderAdapter :
    BaseQuickAdapter<String, OrderViewHolder>(R.layout.item_order) {
    override fun convert(holder: OrderViewHolder, item: String) {
        holder.setText(R.id.tvShopName, "杜康古城酒业商城")
            .setText(R.id.tvOrderStatus, "待付款")
            .setText(R.id.tvOrderTips, "订单将在29分30秒后自动关闭")
            .setText(R.id.tvPrice, "¥965")
            .setText(R.id.tvGoodsNum, "共1件商品 实付：")
        holder.orderAdapter.setNewInstance(mutableListOf("", "", ""))
    }

    override fun onItemViewHolderCreated(viewHolder: OrderViewHolder, viewType: Int) {
        with(viewHolder.getView<RecyclerView>(R.id.rvOrderItem)) {
            adapter = viewHolder.orderAdapter.apply {
                this.setOnItemClickListener { _, view, _ ->
                    this@OrderAdapter.getOnItemClickListener()?.onItemClick(
                        this@OrderAdapter, view, viewHolder.adapterPosition
                    )
                }
            }
        }
    }
}

class OrderViewHolder(view: View) : BaseViewHolder(view) {
    val orderAdapter = OrderProductAdapter()
}
