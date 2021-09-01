@file:Suppress("unused")

package com.xdys.library.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.loadAny
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation

/**
 * 加载原始尺寸的图片，居中显示
 */
fun ImageView.loadCropImage(
    any: Any?, @DrawableRes error: Int = 0, @DrawableRes placeholder: Int = 0
) = loadAny(any) {
    scaleType = ImageView.ScaleType.CENTER_CROP
    if (error != 0) error(error)
    if (placeholder != 0) placeholder(placeholder)
}

/**
 * 加载圆形的图片，原始尺寸，居中显示
 */
fun ImageView.loadCircleImage(
    any: Any?, @DrawableRes error: Int = 0, @DrawableRes placeholder: Int = 0
) = loadAny(any) {
    scaleType = ImageView.ScaleType.CENTER_CROP
    transformations(CircleCropTransformation())
    if (error != 0) error(error)
    if (placeholder != 0) placeholder(placeholder)
}

/**
 * 加载圆角的图片,并居中显示
 */
fun ImageView.loadRoundCornerImage(
    any: Any?, radius: Int = 5, @DrawableRes error: Int = 0, @DrawableRes placeholder: Int = 0
) = loadAny(any) {
    scaleType = ImageView.ScaleType.CENTER_CROP
    transformations(RoundedCornersTransformation(radius.px.toFloat()))
    if (error != 0) error(error)
    if (placeholder != 0) placeholder(placeholder)
}

/**
 * 加载高斯模糊的图片，并居中显示
 */
fun ImageView.loadBlurImage(
    any: Any?, @DrawableRes error: Int = 0, @DrawableRes placeholder: Int = 0
) = loadAny(any) {
    scaleType = ImageView.ScaleType.CENTER_CROP
    transformations(BlurTransformation(context))
    if (error != 0) error(error)
    if (placeholder != 0) placeholder(placeholder)
}