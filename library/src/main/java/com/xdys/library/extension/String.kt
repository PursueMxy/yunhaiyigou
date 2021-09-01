package com.xdys.library.extension

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun String.copy2Clipboard() {
    (context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager)?.setPrimaryClip(
        ClipData.newPlainText(this, this)
    )
}