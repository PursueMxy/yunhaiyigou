package com.xdys.yhyg.adapte.cart

import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.context
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

        }
        if (builder.isNotEmpty()) builder.deleteCharAt(builder.lastIndex)
        return builder.toString()
    }
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


class CartShopProvider(
    val listener: OnCartItemSelectedListener?
) : BaseNodeProvider() {

    override val itemViewType: Int = 0
    override val layoutId: Int = R.layout.item_cart

    override fun convert(holder: BaseViewHolder, item: BaseNode) {
        (item as CartShopEntity)?.let { shop ->
            holder.getView<AppCompatCheckBox>(R.id.cbShopCheck).isChecked = shop.selected
            holder.setText(R.id.tvShopName, shop.shopName)
                .getView<View>(R.id.tvDiscountCoupon).setOnClickListener {
                    popupCoupons.setData().showPopupWindow()
                }
            holder.getView<AppCompatCheckBox>(R.id.cbShopCheck).setOnClickListener {
                shop.selected = !shop.selected
                listener?.changedShop(shop)
                Log.e("嘻嘻嘻", shop.shopName)
            }
        }
    }


    override fun onChildClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        (data as? CartShopEntity)?.let {
            when (view.id) {
                R.id.cbShopCheck -> {
                }
                else -> {
                }
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
        (item as CartProductEntity)?.let {
            holder.getView<AppCompatCheckBox>(R.id.cbCartCheck).isChecked = it.selected
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
     * 店铺选中状态发生变化
     */
    fun changedShop(cartShop: CartShopEntity)

    /**
     * 数量变化
     * @param uiPosition 在adapter上面的位置
     * @param type 类型 0:减 1:加 2:数量
     * @param originNumber 点击时的数字
     * @param maxCount 最大值(库存)
     */
    fun numberChange(uiPosition: Int, type: Int, originNumber: Int, maxCount: Int)

    /**
     * 侧滑删除购物车商品
     */
    fun itemDelete(uiPosition: Int)

    /**
     * 点击事件
     */
    fun itemClick(uiPosition: Int, product: CartProductEntity)
}




