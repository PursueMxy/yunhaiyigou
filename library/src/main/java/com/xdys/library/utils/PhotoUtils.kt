package com.xdys.library.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import ccom.xdys.library.widget.image.GlideEngine
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener

object PhotoUtils {
    fun selectPicture(fragment: Fragment, listener: OnResultCallbackListener<LocalMedia?>) {
        PictureSelector.create(fragment)
            .openGallery(PictureMimeType.ofImage())
            .isGif(true)
            .selectionMode(PictureConfig.SINGLE)
            .isSingleDirectReturn(false)
            .imageEngine(GlideEngine.createGlideEngine())
            .forResult(listener)
    }

    fun selectPicture(activity: Activity, listener: OnResultCallbackListener<LocalMedia?>) {
        PictureSelector.create(activity)
            .openGallery(PictureMimeType.ofImage())
            .isGif(true)
            .selectionMode(PictureConfig.SINGLE)
            .isSingleDirectReturn(false)
            .imageEngine(GlideEngine.createGlideEngine())
            .forResult(listener)
    }
}