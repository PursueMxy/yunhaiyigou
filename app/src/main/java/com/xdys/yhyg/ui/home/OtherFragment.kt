package com.xdys.yhyg.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.xdys.yhyg.adapte.home.BrandSelectionAdapter
import com.xdys.yhyg.adapte.home.GoodsTypeAdapter
import com.xdys.yhyg.adapte.home.HomeGoodsAdapter
import com.xdys.yhyg.databinding.FragmentOtherBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.extension.dp
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration

class OtherFragment : ViewModelFragment<MineViewModel, FragmentOtherBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOtherBinding = FragmentOtherBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by activityViewModels()
    private val goodsTypeAdapter by lazy { GoodsTypeAdapter() }
    private val brandSelectionAdapter by lazy { BrandSelectionAdapter() }
    private val goodsAdapter by lazy { HomeGoodsAdapter() }


    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = OtherFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvType) {
            adapter = goodsTypeAdapter
            layoutManager = GridLayoutManager(requireContext(), 5)
            addItemDecoration(DividerItemDecoration(8.px, 0.px))
        }
        with(rvBrandSelection) {
            adapter = brandSelectionAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
            addItemDecoration(DividerItemDecoration(12.px, 19.px))
        }
        with(rvGoods) {
            adapter = goodsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(DividerItemDecoration(7.dp, 7.dp))
        }
    }

    override fun initData() {
        goodsTypeAdapter.setNewInstance(
            mutableListOf(
                "夏上新", "JK制服", "连衣裙", "卫衣", "衬衫", "针织衫", "牛仔裤",
                "休闲裤", "印花连衣裙", "真丝连衣裙", "印花连衣裙", "真丝连衣裙", "印花连衣裙", "真丝连衣裙", "更多分类"
            )
        )
        brandSelectionAdapter.setNewInstance(mutableListOf("", "", "", "", "", ""))
        goodsAdapter.setDiffNewData(mutableListOf())
    }
}