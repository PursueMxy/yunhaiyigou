package com.xdys.library.base

import android.app.Application
import android.content.Context
import android.view.animation.OvershootInterpolator
import androidx.startup.Initializer
import com.hjq.toast.ToastUtils
import com.hjq.toast.style.ToastBlackStyle
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import com.xdys.library.BuildConfig
import com.xdys.library.extension.init
import timber.log.Timber

class CustomInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        init(context)
        MMKV.initialize(context)
        // 初始化Log打印框架
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        // 初始化Toast
        ToastUtils.init(context as Application, ToastBlackStyle(context))
        // 初始化RefreshLayout
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { innerContext, _ ->
            ClassicsHeader(innerContext).setTextSizeTime(10F)
                .setDrawableArrowSize(14F).setTextSizeTitle(12F)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { innerContext, _ ->
            ClassicsFooter(innerContext)
        }
        SmartRefreshLayout.setDefaultRefreshInitializer { _, layout ->
            layout.setEnableOverScrollBounce(false)
                .setEnableLoadMore(false).setEnableOverScrollDrag(true)
                .setReboundInterpolator(OvershootInterpolator())
                .setHeaderHeight(60F)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}