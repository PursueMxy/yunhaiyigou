package com.xdys.yhyg.ui.web

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import androidx.activity.viewModels
import com.hjq.toast.ToastUtils
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant
import com.xdys.library.config.Constant.Key.EXTRA_ID
import com.xdys.library.config.Constant.Key.EXTRA_TITLE
import com.xdys.library.extension.singleTop
import com.xdys.yhyg.databinding.ActivityWebviewBinding
import com.xdys.yhyg.vm.SetViewModel


class WebViewActivity : ViewModelActivity<SetViewModel, ActivityWebviewBinding>() {

    companion object {
        fun start(context: Context, url: String, title: String? = null) {
            val intent = Intent(context, WebViewActivity::class.java)
                .putExtra(EXTRA_ID, url)
                .putExtra(EXTRA_TITLE, title)
                .singleTop()
            context.startActivity(intent)
        }
    }

    override fun createBinding() = ActivityWebviewBinding.inflate(layoutInflater)

    override val viewModel: SetViewModel by viewModels()

    override fun initUI(savedInstanceState: Bundle?) {
        intent.getStringExtra(EXTRA_TITLE)?.let {
            binding.titleBar.visibility = View.VISIBLE
            binding.titleBar.titleContent = it
        }
        intent.getStringExtra(EXTRA_ID)?.let { binding.webView.loadUrl(it) }
        val homes: String = "android传入到网页里的数据，有参"
//        binding.titleBar.setOnLeftClickListener {
//            binding.webView.loadUrl("javascript:loadSellerInfo('$homes')");
//        }
        binding.webView.addJavaScript(JavaScriptInterface())
    }

    override fun initData() {
        val homes: String = "android传入到网页里的数据，有参"
        binding.webView.loadUrl("javascript:loadSellerInfo('$homes')");
    }

    inner class JavaScriptInterface {
        @JavascriptInterface
        fun startFunction(data: String) {
            Log.e("前端调用", "$data")
            ToastUtils.show("前端调用$data")
        }

        @JavascriptInterface
        fun handleBack(data: String) {
            finish()
            Log.e("前端调用", "返回$data")
        }

    }

}