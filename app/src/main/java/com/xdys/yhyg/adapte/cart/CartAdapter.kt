package com.xdys.yhyg.adapte.cart

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjq.toast.ToastUtils
import com.xdys.library.extension.context
import com.xdys.library.extension.currency
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.cart.CartProductEntity
import com.xdys.yhyg.entity.cart.CartSelectedEntity
import com.xdys.yhyg.entity.cart.CartShopEntity
import com.xdys.yhyg.popup.CouponsPopupWindow
import java.math.BigDecimal

class CartAdapter(
    val listener: OnCartItemSelectedListener? = null
) : BaseNodeAdapter() {

    init {
        addFullSpanNodeProvider(CartShopProvider(listener))
        addNodeProvider(CartProductProvider(listener))
    }

    // 判断是否是编辑模式
    var editMode = false
        set(value) {
            if (field != value) {
                field = value
                // 进入编辑模式时，全部未选中
                if (field) changeAllProductStatus(false)

            }
        }

    val checkStatusEntity = CartSelectedEntity()

    override fun getItemType(data: List<BaseNode>, position: Int): Int =
        when (data[position]) {
            is CartShopEntity -> 0
            is CartProductEntity -> 1
            else -> 1
        }


    /**
     * 刷新选中状态的entity
     * @param needAllCheck 是否需要进行全部选中、取消全选操作
     * @param allCheck 全部选中、取消全选
     */
    fun refreshStatusEntity(
        needAllCheck: Boolean = false,
        allCheck: Boolean = false,
        cartId: Long = 0,
        number: Int = 0,
    ): CartSelectedEntity {
        var initPrice = BigDecimal(0.0)
        checkStatusEntity.allSelected = true
        checkStatusEntity.selectAny = false
        for (shop in data) (shop as? CartShopEntity)?.let {
            it.selected = allCheck
        }
        for (product in data) (product as? CartProductEntity)?.let {
            it.selected = allCheck
        }

        if (needAllCheck || (cartId > 0 && number > 0)) notifyDataSetChanged()
        checkStatusEntity.totalPrice = initPrice.toString()
        return checkStatusEntity
    }

    fun refreshAllCart(shopId: String) {
        for (shop in data) (shop as? CartShopEntity)?.let {
            if (shop.shopId == shopId) {
                for (goods in shop.goodsList) {
                    if (!goods.selected) {
                        shop.selected = false
                        notifyDataSetChanged()
                        return
                    } else {
                        shop.selected = true
                    }
                }
            }
            notifyDataSetChanged()
        }
    }

    /**
     * 刷新当前item
     */
    fun refreshShopEntity(cartShop: CartShopEntity) {
        for (product in cartShop.goodsList) {
            product.selected = cartShop.selected
        }
        notifyDataSetChanged()
    }

    /**
     * 获取所有已选择的商品购物车id，用,隔开
     */
    fun getCartIds(): String {
        val builder = StringBuilder()
        for (product in data) (product as? CartProductEntity)?.let {
            if (product.selected) builder.append(product.userShoppingCartId).append(",")
        }
        if (builder.isNotEmpty()) builder.deleteCharAt(builder.lastIndex)
        return builder.toString()
    }
}


class CartShopProvider(
    val listener: OnCartItemSelectedListener?
) : BaseNodeProvider() {

    override val itemViewType: Int = 0
    override val layoutId: Int = R.layout.item_cart

    override fun convert(holder: BaseViewHolder, item: BaseNode) {
        (item as CartShopEntity)?.let { shop ->
            holder.getView<ImageView>(R.id.cbShopCheck).isSelected = shop.selected
            holder.setText(R.id.tvShopName, shop.shopName)
                .getView<View>(R.id.tvDiscountCoupon).setOnClickListener {
                    popupCoupons.setData().showPopupWindow()
                }
            holder.getView<ImageView>(R.id.cbShopCheck).setOnClickListener {
                shop.selected = !shop.selected
                listener?.changedShop(shop)
            }
        }
    }

}

class CartProductProvider(
    val listener: OnCartItemSelectedListener?
) : BaseNodeProvider() {

    override val itemViewType: Int = 1
    override val layoutId: Int = R.layout.item_cart_goods

    override fun convert(holder: BaseViewHolder, item: BaseNode) {
        (item as CartProductEntity)?.let { cartProduct ->
            holder.setText(R.id.tvGoodsName, cartProduct.goodsSpu?.name)
            holder.setText(R.id.tvPrice, (cartProduct.goodsSpu?.priceDown?.currency()))
            holder.setText(R.id.tvNumber, cartProduct.quantity.toString())
            cartProduct.specs?.let {
                holder.setText(R.id.tvSpuCode, it[0].specValueName)
            }
            holder.getView<ImageView>(R.id.cbCartChecks).isSelected = cartProduct.selected
            holder.getView<ImageView>(R.id.cbCartChecks).setOnClickListener {
                cartProduct.selected = !cartProduct.selected
                listener?.changeProduct(cartProduct)
                it.isSelected = cartProduct.selected
            }
            holder.getView<ImageView>(R.id.ivSubtract).setOnClickListener {
                if (cartProduct.quantity > 1) {
                    listener?.numberChange(
                        cartProduct,
                        (cartProduct.quantity - 1).toInt(),
                        cartProduct.quantity.toInt()
                    )
                } else {
                    ToastUtils.show("最少只能一件")
                }
            }
            holder.getView<ImageView>(R.id.ivAdd).setOnClickListener {
                if (cartProduct.quantity < cartProduct.goodsSku.stock) {
                    listener?.numberChange(
                        cartProduct,
                        (cartProduct.quantity + 1).toInt(),
                        cartProduct.quantity.toInt()
                    )
                } else {
                    ToastUtils.show("超过库存数量")
                }
            }

        }
    }

}

private val popupCoupons: CouponsPopupWindow by lazy {
    CouponsPopupWindow(context) {
    }
}

/**
 * 修改全部商品的选中状态
 */
fun changeAllProductStatus(checkAll: Boolean) {

}


interface OnCartItemSelectedListener {
    /**
     * 选中状态发生变化
     */
    fun changed()

    /**
     * 商品选择更新店铺
     */
    fun changeProduct(cartProduct: CartProductEntity)

    /**
     * 店铺选中状态发生变化
     */
    fun changedShop(cartShop: CartShopEntity)

    /**
     * 数量变化
     * @param cartProduct 点击的商品
     * @param type 类型 0:减 1:加 2:数量
     * @param originNumber 点击时的数字
     */
    fun numberChange(cartProduct: CartProductEntity, type: Int, originNumber: Int)

    /**
     * 侧滑删除购物车商品
     */
    fun itemDelete(uiPosition: Int)

    /**
     * 点击事件
     */
    fun itemClick(uiPosition: Int, product: CartProductEntity)
}




