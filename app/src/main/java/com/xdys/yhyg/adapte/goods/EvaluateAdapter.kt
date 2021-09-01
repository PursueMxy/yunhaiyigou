package com.xdys.yhyg.adapte.goods

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.home.EvaluateImgAdapter
import com.xdys.library.extension.dp
import com.xdys.library.extension.loadCircleImage
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration

class EvaluateAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_evaluation) {

    val evaluateImgAdapter = EvaluateImgAdapter()

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvUserName, "小杜小杜")
            .setText(R.id.tvTime, "2021-06-07  9:27")
            .setText(R.id.tvSpecification, "规格：【迎雙喜】500ML单瓶")
            .setText(R.id.tvContent, "包装设计精美，大气沉稳上档次，口感也不错，物流也很快，一次不错的购物体验！")
            .getView<ImageView>(R.id.ivPortrait).loadCircleImage(R.mipmap.du_kang_jiu)
        holder.getView<AppCompatRatingBar>(R.id.rating).rating = 4f
        evaluateImgAdapter.setNewInstance(mutableListOf("", "", ""))
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        with(viewHolder.getView<RecyclerView>(R.id.rvEvaluate)) {
            adapter = evaluateImgAdapter
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(DividerItemDecoration(13.dp, 10.px))
        }

    }
}