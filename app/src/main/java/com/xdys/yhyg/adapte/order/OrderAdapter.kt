package com.xdys.yhyg.adapte.order

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.order.OrderEntity

class OrderAdapter :
    BaseQuickAdapter<OrderEntity, OrderViewHolder>(R.layout.item_order), LoadMoreModule {

    init {
        setDiffCallback(OrderDiffCallback())
    }

    override fun convert(holder: OrderViewHolder, item: OrderEntity) {
        holder.setText(R.id.tvShopName, item.order_goods_items.get(0).vendorName)
            .setText(
                R.id.tvOrderStatus, when (item.status) {
                    "0" -> "待付款"
                    "1" -> "待发货"
                    "2" -> "待收货"
                    "3" -> "待评价"
                    "4" -> "已完成"
                    else -> "未知"
                }
            )
//            .setText(R.id.tvOrderTips, "订单将在29分30秒后自动关闭")
            .setText(R.id.tvPrice, item.orders_count)
            .setText(R.id.tvGoodsNum, "共${item.order_goods_items.size}件商品 实付：")
        for (goods in item.order_goods_items) {

        }
        holder.orderAdapter.setNewInstance(item.order_goods_items)
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

class OrderDiffCallback : DiffUtil.ItemCallback<OrderEntity>() {
    override fun areItemsTheSame(oldItem: OrderEntity, newItem: OrderEntity): Boolean {
        return oldItem.orders_id == newItem.orders_id
    }

    override fun areContentsTheSame(oldItem: OrderEntity, newItem: OrderEntity): Boolean {
        return oldItem.orders_count == newItem.orders_count
    }
}

