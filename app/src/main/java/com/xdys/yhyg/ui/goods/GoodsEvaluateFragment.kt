package com.xdys.yhyg.ui.goods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xdys.yhyg.adapte.goods.EvaluateAdapter
import com.xdys.yhyg.adapte.goods.EvaluateLabelAdapter
import com.xdys.yhyg.adapte.goods.EvaluateTypeAdapter
import com.xdys.yhyg.databinding.FragmentGoodsEvaluateBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.entity.goods.Evaluate

class GoodsEvaluateFragment :
    ViewModelFragment<MineViewModel, FragmentGoodsEvaluateBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGoodsEvaluateBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()

    private val evaluateAdapter by lazy { EvaluateAdapter() }

    private val evaluateLabelAdapter by lazy { EvaluateLabelAdapter() }

    private val evaluateTypeAdapter by lazy { EvaluateTypeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvEvaluate) {
            adapter = evaluateAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        with(rvEvaluateLabel) {
            adapter = evaluateLabelAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            addItemDecoration(DividerItemDecoration(12.px, 17.px))
        }
        with(rvEvaluateType) {
            adapter = evaluateTypeAdapter
            layoutManager = GridLayoutManager(requireContext(), 5)
            addItemDecoration(DividerItemDecoration(12.px, 17.px))
        }
        tvPercentage.text = "98%"
    }

    override fun initData() {
        evaluateAdapter.setNewInstance(mutableListOf(Evaluate(), Evaluate(),Evaluate()))
        evaluateLabelAdapter.setNewInstance(mutableListOf("口味极佳6", "香气十足6", "酒香甘醇6", "品牌信赖6"))
        evaluateTypeAdapter.setNewInstance(mutableListOf("全部", "有图6+", "好评6+", "中评6+", "差评+"))
    }
}