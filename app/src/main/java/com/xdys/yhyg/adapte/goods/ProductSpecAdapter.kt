package com.xdys.yhyg.adapte.goods

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.xdys.yhyg.R
import com.xdys.yhyg.entity.goods.SkuItem
import com.xdys.yhyg.entity.goods.SpecItem
import com.xdys.yhyg.entity.goods.skuEntity
import com.xdys.yhyg.entity.goods.specs

class ProductSpecAdapter(private val valueClickListener: OnSpecValueClickListener) :
    BaseQuickAdapter<SkuItem, SpecViewHolder>(R.layout.item_product_spec) {

    private val pool = RecyclerView.RecycledViewPool()

    override fun convert(helper: SpecViewHolder, item: SkuItem) {
        helper.setText(R.id.tvProductSpecType, item.value)
        helper.currentSpecIndex = helper.adapterPosition
        helper.valueClickListener = valueClickListener
        helper.adapter.setNewInstance(item.leaf)
    }

    override fun onItemViewHolderCreated(viewHolder: SpecViewHolder, viewType: Int) {
        with(viewHolder.getView<RecyclerView>(R.id.rvSpec)) {
            setRecycledViewPool(pool)
            layoutManager = FlexboxLayoutManager(context)
            adapter = viewHolder.adapter
        }
    }
}

class SpecViewHolder(view: View) : BaseViewHolder(view) {
    val adapter = ProductSpecValueAdapter().apply {
        setOnItemClickListener { _, _, position ->
            if (data[position].selected) return@setOnItemClickListener
            valueClickListener?.onValueClick(
                currentSpecIndex, position
            )
            notifyDataSetChanged()
        }
    }
    var currentSpecIndex: Int = 0
    var valueClickListener: OnSpecValueClickListener? = null
}

class ProductSpecValueAdapter :
    BaseQuickAdapter<SpecItem, BaseViewHolder>(R.layout.item_product_spec_value) {
    override fun convert(helper: BaseViewHolder, item: SpecItem) {
        helper.setText(R.id.btnSpecValue, item.value)
            .getView<TextView>(R.id.btnSpecValue).isSelected = item.selected
    }
}

interface OnSpecValueClickListener {
    fun onValueClick(specIndex: Int, valueIndex: Int)
}