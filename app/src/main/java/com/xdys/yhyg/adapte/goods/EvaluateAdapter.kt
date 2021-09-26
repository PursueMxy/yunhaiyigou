package com.xdys.yhyg.adapte.goods

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xdys.library.extension.dp
import com.xdys.library.extension.loadCircleImage
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.home.EvaluateImgAdapter
import com.xdys.yhyg.entity.goods.Evaluate

class EvaluateAdapter : BaseQuickAdapter<Evaluate, BaseViewHolder>(R.layout.item_evaluation) {

    val evaluateImgAdapter = EvaluateImgAdapter()

    init {
        setDiffCallback(EvaluateDiffCallback())
    }

    override fun convert(holder: BaseViewHolder, item: Evaluate) {
        holder.setText(R.id.tvUserName, item.userName)
            .setText(R.id.tvTime, item.time)
            .setText(R.id.tvSpecification, item.specification)
            .setText(R.id.tvContent, item.content)
            .getView<ImageView>(R.id.ivPortrait).loadCircleImage(R.mipmap.du_kang_jiu)
        holder.getView<AppCompatRatingBar>(R.id.rating).rating = 4f
        evaluateImgAdapter.setNewInstance(item.imgList)
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        with(viewHolder.getView<RecyclerView>(R.id.rvEvaluate)) {
            adapter = evaluateImgAdapter
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(DividerItemDecoration(13.dp, 10.px))
        }
    }
}


class EvaluateDiffCallback : DiffUtil.ItemCallback<Evaluate>() {
    override fun areItemsTheSame(oldItem: Evaluate, newItem: Evaluate): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Evaluate, newItem: Evaluate): Boolean {
        return oldItem.content == newItem.content && oldItem.userName == newItem.userName
                && oldItem.time == newItem.time && oldItem.specification == newItem.specification
                && oldItem.rating == newItem.rating
    }
}