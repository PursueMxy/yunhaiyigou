package com.xdys.library.extension

import android.content.Context

private lateinit var application: Context

val context: Context
    get() = application

fun init(context: Context) {
    application = context
}