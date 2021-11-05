package com.xdys.yhyg.adapte.cart

import android.widget.ImageView
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.currency
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.order.BuyShop
import com.xdys.yhyg.entity.order.OrderGoods

class ConfirmOrderAdapter : BaseNodeAdapter() {

    init {
        addFullSpanNodeProvider(ShopProvider())
        addNodeProvider(OrderGoodsProvider())
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int =
        when (data[position]) {
            is BuyShop -> 0
            is OrderGoods -> 1
            else -> 1
        }
}

class ShopProvider : BaseNodeProvider() {

    override val itemViewType: Int = 0
    override val layoutId: Int = R.layout.item_order_shop

    override fun convert(holder: BaseViewHolder, item: BaseNode) {
        (item as BuyShop)?.let {
            holder.setText(R.id.tvShopName, "杜康古城酒业")
        }
    }
}


class OrderGoodsProvider : BaseNodeProvider() {

    override val itemViewType: Int = 1
    override val layoutId: Int = R.layout.item_order_goods

    override fun convert(holder: BaseViewHolder, item: BaseNode) {
        (item as OrderGoods)?.let {
            holder.setText(R.id.tvGoodsName, it.goodsSpuVo?.name)
                .setText(R.id.tvSpecs, it.goodsSkuVo?.specName)
                .setText(R.id.tvPrice, it.paymentPrice?.currency())
                .setText(R.id.tvNumber, "X${it.quantity}")
                .getView<ImageView>(R.id.ivGoods)
                .loadRoundCornerImage(it.goodsSpuVo?.picUrls?.get(0))
        }
    }
}
