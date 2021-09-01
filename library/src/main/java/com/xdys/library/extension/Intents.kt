package com.xdys.library.extension

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.xdys.library.R
import java.net.URLEncoder

fun Intent.singleTop(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
}

fun Intent.clearTop(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
}

fun Intent.clearTask(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
}

fun Intent.newTask(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
}

fun Intent.single(): Intent = apply {
    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
}

fun dial(number: String) = Intent().apply {
    action = Intent.ACTION_DIAL
    data = Uri.parse(context.getString(R.string.dial_format, number))
}

/**
 * 打开应用设置页面
 */
@SuppressLint("QueryPermissionsNeeded")
fun toSetting() = Intent().apply {
    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    data = Uri.parse(context.getString(R.string.package_format, context.packageName))
    if (resolveActivity(context.packageManager) == null) {
        action = Settings.ACTION_SETTINGS
        data = null
    }
}

/**
 * 打开浏览器
 */
fun browser(url: String) = Intent().apply {
    action = Intent.ACTION_VIEW
    data = Uri.parse(url)
}

/**
 * 地图导航
 */
fun navigation(lat: Double, lng: Double, targetName: String?) = Intent().apply {
    action = Intent.ACTION_VIEW
    data = Uri.parse(buildString {
        append(context.getString(R.string.geo_format)).append(lat)
        append(context.getString(R.string.comma)).append(lng)
        if (!targetName.isNullOrBlank()) {
            append(context.getString(R.string.query_format)).append(
                URLEncoder.encode(
                    targetName,
                    Charsets.UTF_8.name()
                )
            )
        }
    })
}

fun home() = Intent().apply {
    action = Intent.ACTION_MAIN
    addCategory(Intent.CATEGORY_HOME)
}