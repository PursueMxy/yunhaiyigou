package com.xdys.yhyg.adapte.classify

import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.ui.classify.SingleCategoryActivity
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration

class ShopAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_shop) {

    private val goodsItemAdapter by lazy { GoodsPriceAdapter() }

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvShopName, "梵希蔓服饰旗舰店")
            .setText(R.id.tvAttention, "4.5万人关注")
            .getView<ImageView>(R.id.ivLogo).loadRoundCornerImage(R.mipmap.mall_logo)
        goodsItemAdapter.setNewInstance(mutableListOf("", "", ""))
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        with(viewHolder.getView<RecyclerView>(R.id.rvGoodsItem)) {
            adapter = goodsItemAdapter
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(DividerItemDecoration(13.px, 11.px))
        }
        with(goodsItemAdapter) {
            setOnItemClickListener { _, view, position ->
                SingleCategoryActivity.start(context, "", "")
            }
        }
    }
}
