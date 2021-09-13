package com.xdys.yhyg.adapte.cart

import android.view.View
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.context
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.cart.CartProductEntity
import com.xdys.yhyg.entity.cart.CartShopEntity
import com.xdys.yhyg.popup.CouponsPopupWindow

class CartAdapter() : BaseNodeAdapter() {

    init {
        addFullSpanNodeProvider(CartShopProvider())
        addNodeProvider(CartProductProvider())
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int =
        when (data[position]) {
            is CartShopEntity -> 0
            is CartProductEntity -> 1
            else -> 1
        }
}


class CartShopProvider : BaseNodeProvider() {

    override val itemViewType: Int = 0
    override val layoutId: Int = R.layout.item_cart

    override fun convert(holder: BaseViewHolder, item: BaseNode) {
        (item as CartShopEntity)?.let {
            holder.setText(R.id.tvShopName, it.shopName)
                .getView<View>(R.id.tvDiscountCoupon).setOnClickListener {
                    popupCoupons.setData().showPopupWindow()
                }
        }
    }
}

class CartProductProvider : BaseNodeProvider() {

    override val itemViewType: Int = 1
    override val layoutId: Int = R.layout.item_cart_goods

    override fun convert(holder: BaseViewHolder, item: BaseNode) {
    }
}

private val popupCoupons: CouponsPopupWindow by lazy {
    CouponsPopupWindow(context) {
    }
}

