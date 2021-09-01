package com.xdys.library.extension

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("strokeThrough")
fun setStrokeThrough(view: TextView, strokeThrough: Boolean) {
    view.paint.isStrikeThruText = strokeThrough
}

@BindingAdapter(value = ["imgUrl", "radius", "circle"], requireAll = false)
fun loadImgUrl(view: ImageView, imgUrl: String?, radius: Int?, circle: Boolean?) {
    if (imgUrl.isNullOrBlank()) return
    if (circle == true) {
        view.loadCircleImage(imgUrl)
    } else if (radius != null && radius > 0) {
        view.loadRoundCornerImage(imgUrl, radius)
    } else if (view.scaleType == ImageView.ScaleType.FIT_CENTER) {
        view.loadCropImage(imgUrl)
    } else view.loadRoundCornerImage(imgUrl)
}