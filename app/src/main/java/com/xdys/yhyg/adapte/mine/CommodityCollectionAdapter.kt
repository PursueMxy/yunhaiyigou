package com.xdys.yhyg.adapte.mine

import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.currency
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.mine.CollectEntity
import com.xdys.yhyg.entity.mine.CollectStatusUIEntity
import java.math.BigDecimal
import java.math.RoundingMode

class CommodityCollectionAdapter :
    BaseQuickAdapter<CollectEntity, BaseViewHolder>(R.layout.item_commodity_collection),
    LoadMoreModule {

    init {
        setDiffCallback(CommodityCollectionDiffCallback())
        addChildClickViewIds(R.id.cbCartCheck)
    }

    val collectionParamsLiveData by lazy { MutableLiveData<CollectStatusUIEntity>() }

    private var isManagement: Boolean = false
    private val builder = StringBuilder()
    private val zero = BigDecimal.ZERO

    override fun convert(holder: BaseViewHolder, item: CollectEntity) {
        holder.setText(R.id.tvGoodsName, item.goodsSpuApiVo?.name)
            .setText(R.id.tvPrice, item.goodsSpuApiVo?.priceUp?.currency())
            .getView<ImageView>(R.id.ivGoods).loadRoundCornerImage(item.goodsSpuApiVo?.picUrls)
        var ivSelectedStatus = holder.getView<ImageView>(R.id.cbCartCheck)
        ivSelectedStatus.isVisible = isManagement
        ivSelectedStatus.isSelected = item.selected
    }

    /**
     * 刷新购物车商品选中状态
     * @param position 商品在列表中的position
     */
    fun changeProductSelectStatus(position: Int) {
        collectionParamsLiveData.postValue((collectionParamsLiveData.value
            ?: CollectStatusUIEntity()).apply {
            // 初始化参数
            allSelected = true
            anySelected = false
            builder.setLength(0)
            var price = zero
            // for循环计算
            for (index in data.indices) data[index].let { product ->
                // 先判断是否需要修改商品状态
                if (index == position) product.selected = !product.selected
                if (product.selected) {
                    builder.append(product.id).append(context.getString(R.string.comma))
                    // 判断是否有选择商品
                    anySelected = true
                } else allSelected = false // 判断是否全选
            }
            // 赋值
            cartIds = if (anySelected) builder.substring(0, builder.lastIndex) else
                builder.toString()
            totalPrice = price.setScale(2, RoundingMode.HALF_UP).toPlainString()
        })
        // 刷新UI
        notifyItemChanged(position)
    }


    /**
     * 修改全部商品的选中状态
     */
    fun changeAllProductStatus(checkAll: Boolean) {
        collectionParamsLiveData.postValue((collectionParamsLiveData.value
            ?: CollectStatusUIEntity()).apply {
            // 初始化参数
            allSelected = true
            anySelected = false
            builder.setLength(0)
            var price = zero
            // for循环计算
            for (index in data.indices) data[index].let { product ->
                product.selected = checkAll
                // 再计算价格、拼接cartIds
                if (product.selected) {
                    builder.append(product.id).append(context.getString(R.string.comma))
                    // 判断是否有选职责商品
                    anySelected = true
                } else allSelected = false // 判断是否全选
            }
            // 赋值
            cartIds = if (anySelected) builder.substring(0, builder.lastIndex) else
                builder.toString()
            totalPrice = price.setScale(2, RoundingMode.HALF_UP).toPlainString()
        })
        notifyDataSetChanged()
    }


    fun changeProductStatus(management: Boolean) {
        isManagement = management
        notifyDataSetChanged()
    }

}

class CommodityCollectionDiffCallback : DiffUtil.ItemCallback<CollectEntity>() {
    override fun areItemsTheSame(
        oldItem: CollectEntity,
        newItem: CollectEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CollectEntity,
        newItem: CollectEntity
    ): Boolean {
        return oldItem.type == newItem.type && oldItem.relationId == oldItem.relationId
    }
}

