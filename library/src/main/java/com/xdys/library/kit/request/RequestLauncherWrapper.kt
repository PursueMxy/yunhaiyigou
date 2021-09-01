package com.xdys.library.kit.request

import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher

class RequestLauncherWrapper(private val launcher: ActivityResultLauncher<Array<String>>) {

    val extra: Bundle = Bundle()

    fun launch(arrays: Array<String>) = launcher.launch(arrays)
}