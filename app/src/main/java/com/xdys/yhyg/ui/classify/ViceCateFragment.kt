package com.xdys.yhyg.ui.classify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.databinding.FragmentViceCateBinding
import com.xdys.yhyg.entity.classify.InteractionEntity
import com.xdys.yhyg.ui.goods.GoodsDetailActivity
import com.xdys.yhyg.vm.ClassificationViewModel

class ViceCateFragment : ViewModelFragment<ClassificationViewModel, FragmentViceCateBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentViceCateBinding.inflate(inflater, container, false)

    override val viewModel: ClassificationViewModel by viewModels()
    private val gson by lazy { Gson() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.webView.loadUrl("http://192.168.2.2:8080/category")
        binding.webView.addJavaScript(JavaScriptInterface())
    }

    inner class JavaScriptInterface {
        @JavascriptInterface
        fun jumpDetail(data: String) {
            val interaction = Gson().fromJson(data, InteractionEntity::class.java)
            interaction.spuId?.let { GoodsDetailActivity.start(requireContext(), it) }
        }
    }

}