package com.xdys.yhyg.ui.classify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.extension.dp
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.adapte.cart.CateGoodsAdapter
import com.xdys.yhyg.adapte.classify.CateLeftAdapter
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

    // 左侧
    private val cateAdapter = CateLeftAdapter { }

    private val cateGoodsAdapter by lazy { CateGoodsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding.webView.loadUrl("http://192.168.2.2:8080/category")
//        binding.webView.addJavaScript(JavaScriptInterface())
        with(binding.rvLeft) {
            adapter = cateAdapter
        }
        with(cateAdapter) {
            setOnItemClickListener { _, view, position ->
                data[position].id?.let { viewModel.goodsSpu(it) }
                scrollToPosition(position)
            }
        }
        with(binding.rvRight) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(DividerItemDecoration(7.dp, 7.dp))
            adapter = cateGoodsAdapter
        }
        with(cateGoodsAdapter){
                setOnItemClickListener { _, _, position ->
                    data[position].id?.let { GoodsDetailActivity.start(requireContext(), it) }
                }
        }
    }

    override fun initData() {
        super.initData()
        viewModel.goodsClassify()
    }


    inner class JavaScriptInterface {
        @JavascriptInterface
        fun jumpDetail(data: String) {
            val interaction = Gson().fromJson(data, InteractionEntity::class.java)
            interaction.spuId?.let { GoodsDetailActivity.start(requireContext(), it) }
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.goodsClassifyLiveData.observe(this) {
            cateAdapter.setNewInstance(it)
            if (it.size > 0) {
                it[0].id?.let { it1 -> viewModel.goodsSpu(it1) }
            }
        }
        viewModel.goodsSpuLiveData.observe(this) {
            cateGoodsAdapter.setDiffNewData(it.records)
        }
    }

}