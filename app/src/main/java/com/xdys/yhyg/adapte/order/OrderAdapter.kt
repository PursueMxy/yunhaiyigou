package com.xdys.yhyg.adapte.order

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
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
        addChildClickViewIds(R.id.btnOrderStatus, R.id.btnTwoStatus, R.id.btnThreeStatus)
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
        val btnOrderStatus = holder.getView<TextView>(R.id.btnOrderStatus)
        val btnTwoStatus = holder.getView<TextView>(R.id.btnTwoStatus)
        val btnThreeStatus = holder.getView<TextView>(R.id.btnThreeStatus)
        btnThreeStatus.isVisible = (item.status == "3" || item.status == "4" || item.status == "5")
        when (item.status) {
            "0" -> {
                btnOrderStatus.text = "去付款"
                btnTwoStatus.text = "取消订单"
            }
            "1" -> {
                btnOrderStatus.text = "催发货"
                btnTwoStatus.text = "申请退款"
            }
            "2" -> {
                btnOrderStatus.text = "催发货"
                btnTwoStatus.text = "申请退款"
            }
            "3" -> {
                btnOrderStatus.text = "确认收货"
                btnTwoStatus.text = "查看物流"
                btnThreeStatus.text = "退换/售后"
            }
            "4" -> {
                btnOrderStatus.text = "晒单评价"
                btnTwoStatus.text = "查看物流"
                btnThreeStatus.text = "退换/售后"
            }
            "5" -> {
                btnOrderStatus.text = "再次购买"
                btnTwoStatus.text = "查看物流"
                btnThreeStatus.text = "退换/售后"
            }
        }
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

