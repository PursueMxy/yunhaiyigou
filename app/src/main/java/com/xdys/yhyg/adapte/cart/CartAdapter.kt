package com.xdys.yhyg.adapte.cart

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.cart.CartProductEntity
import com.xdys.yhyg.entity.cart.CartShopEntity
import com.xdys.library.extension.loadRoundCornerImage

class CartAdapter(
    val listener: OnCartItemSelectedListener? = null
) : BaseNodeAdapter() {

    companion object {
        val TYPE_SHOP = 0
        val TYPE_PRODUCT = 1
    }

    init {
        addFullSpanNodeProvider(CartShopProvider())
        addNodeProvider(CartProductProvider(listener))
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        return when (data[position]) {
            is CartShopEntity -> TYPE_SHOP
            else -> TYPE_PRODUCT
        }
    }

    class CartShopProvider : BaseNodeProvider() {
        override val itemViewType: Int = TYPE_SHOP
        override val layoutId: Int = R.layout.item_cart

        override fun convert(helper: BaseViewHolder, data: BaseNode) {
            (data as? CartShopEntity)?.let {
                helper.setText(R.id.tvShopName, data.shopName)
            }
        }

        override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
            (data as? CartShopEntity)?.let {

            }
        }
    }

    class CartProductProvider(
        val listener: OnCartItemSelectedListener?
    ) : BaseNodeProvider() {
        override val itemViewType: Int = TYPE_PRODUCT
        override val layoutId: Int = R.layout.item_cart_goods

        init {
            addChildClickViewIds(R.id.cbCartCheck)
        }

        override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
            Log.e("是是是", "错误地方")
        }

        override fun convert(helper: BaseViewHolder, data: BaseNode) {
            helper.getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(R.mipmap.du_kang_jiu)
        }

        override fun onChildClick(
            helper: BaseViewHolder, view: View, data: BaseNode, position: Int
        ) {
            (data as? CartProductEntity)?.let {
                when (view.id) {
                    // 选中购物车商品
                    R.id.cbCartCheck -> {
                        it.selected = !it.selected
                        listener?.changed()
                    }
                    else -> Unit
                }
            }
        }
    }
}

interface OnCartItemSelectedListener {
    /**
     * 选中状态发生变化
     */
    fun changed()

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