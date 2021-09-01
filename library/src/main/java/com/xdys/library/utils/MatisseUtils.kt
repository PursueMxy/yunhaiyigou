package com.xdys.library.utils

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.filter.Filter
import com.zhihu.matisse.internal.entity.CaptureStrategy
import com.zhihu.matisse.internal.entity.IncapableCause
import com.zhihu.matisse.internal.entity.Item
import com.zhihu.matisse.internal.utils.PathUtils
import java.io.File


object MatisseUtils {
    fun selectPicture(activity: Activity, requestCode: Int, needCapture: Boolean = true, count: Int = 1) {
        Matisse.from(activity)
            .choose(MimeType.of(MimeType.PNG, MimeType.JPEG))
            .addFilter(object : Filter() {
                override fun filter(context: Context?, item: Item?): IncapableCause? {
                    if (item != null && File(PathUtils.getPath(context, item.contentUri)).exists())
                        return null
                    return IncapableCause(IncapableCause.TOAST, "图片为空")
                }
    
                override fun constraintTypes(): MutableSet<MimeType> =
                    mutableSetOf(MimeType.PNG, MimeType.JPEG)
            })
            .showSingleMediaType(true)
            .showPreview(false)
            .countable(count > 1)
            .spanCount(4)
            .maxSelectable(count)
            .imageEngine(GlideEngine())
            .apply {
                if (needCapture) {
                    //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
                    capture(true)
                    captureStrategy(CaptureStrategy(true, "${activity.packageName}.fileProvider"))
                }
            }.forResult(requestCode)
    }
    
    fun selectPicture(fragment: Fragment, requestCode: Int, needCapture: Boolean = true, count: Int = 1) {
        Matisse.from(fragment)
            .choose(MimeType.of(MimeType.PNG, MimeType.JPEG))
            .addFilter(object : Filter() {
                override fun filter(context: Context?, item: Item?): IncapableCause? {
                    if (item != null && File(PathUtils.getPath(context, item.contentUri)).exists())
                        return null
                    return IncapableCause(IncapableCause.TOAST, "图片为空")
                }
    
                override fun constraintTypes(): MutableSet<MimeType> =
                    mutableSetOf(MimeType.PNG, MimeType.JPEG)
            })
            .showSingleMediaType(true)
            .showPreview(false)
            .countable(count > 1)
            .spanCount(4)
            .maxSelectable(count)
            .imageEngine(GlideEngine())
            .apply {
                if (needCapture) {
                    //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
                    capture(true)
                    captureStrategy(CaptureStrategy(true, "${fragment.activity?.packageName}.fileProvider"))
                }
            }.forResult(requestCode)
    }
}