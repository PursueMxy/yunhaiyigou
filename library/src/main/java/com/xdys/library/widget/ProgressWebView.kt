package com.xdys.library.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.webkit.*
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.content.res.use
import androidx.databinding.BindingAdapter
import com.google.gson.Gson
import com.hjq.toast.ToastUtils
import com.xdys.library.R
import com.xdys.library.extension.px


@SuppressLint("SetJavaScriptEnabled")
class ProgressWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val javaScriptName = "android"

    private var onReceivedTitle: ((String) -> Unit)? = null
    var showProgress = true
        set(value) {
            if (field != value && !value && progressBar.visibility == VISIBLE) {
                progressBar.visibility = GONE
            }
            field = value
        }
    var urlClickable = true

    private val webView = lazy {
        WebView(context.applicationContext, null, android.R.attr.webViewStyle).apply {
            overScrollMode = View.OVER_SCROLL_NEVER
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.setSupportZoom(false)
            settings.domStorageEnabled = true
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            setBackgroundColor(Color.TRANSPARENT)
        }
    }
    private val progressBar by lazy {
        ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.ProgressWebView).use {
            showProgress = it.getBoolean(R.styleable.ProgressWebView_showProgress, true)
        }
        addView(
            progressBar, LayoutParams(LayoutParams.MATCH_PARENT, 3.px).apply {
                gravity = Gravity.TOP
            }
        )
        progressBar.visibility = GONE
    }

    override fun onDetachedFromWindow() {
        if (webView.isInitialized() && webView.value.parent != null) {
            webView.value.removeJavascriptInterface(javaScriptName)
            webView.value.stopLoading()
            removeView(webView.value)
        }
        super.onDetachedFromWindow()
    }

    fun loadUrl(url: String) {
        if (webView.value.parent == null) {
            initWebView()
            addView(
                webView.value, 0, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            )
        }
        webView.value.loadUrl(url)

    }



    @SuppressLint("JavascriptInterface")
    fun addJavaScript(jsInterface: Any) {
        webView.value.addJavascriptInterface(jsInterface, javaScriptName)
    }


    /**
     * 接收webView的回退事件
     */
    fun handleBackPress(): Boolean {
        return if (webView.isInitialized() && webView.value.canGoBack()) {
            webView.value.goBack()
            ToastUtils.show("点击返回")
            true
        } else false
    }

    /**
     * 接收WebView的标题
     */
    fun receivedTitle(onReceivedTitle: (title: String) -> Unit) {
        this.onReceivedTitle = onReceivedTitle
    }

    private fun initWebView() = with(webView.value) {
        webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (showProgress) progressBar.progress = newProgress
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                title?.let { if (view?.url?.contains(title) != true) onReceivedTitle?.invoke(it) }
            }
        }
        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?, request: WebResourceRequest?
            ): Boolean = if (urlClickable) super.shouldOverrideUrlLoading(view, request) else true

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                if (showProgress) progressBar.visibility = VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                if (showProgress) progressBar.visibility = GONE
            }
        }
    }
}

@BindingAdapter("url")
fun loadUrl(view: ProgressWebView, url: String?) {
    if (url.isNullOrBlank()) return
    view.loadUrl(url)
}