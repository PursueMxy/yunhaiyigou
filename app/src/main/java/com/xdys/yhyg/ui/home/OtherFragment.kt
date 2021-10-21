package com.xdys.yhyg.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.config.Constant.Key.EXTRA_ID
import com.xdys.library.extension.dp
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.adapte.home.BrandSelectionAdapter
import com.xdys.yhyg.adapte.home.GoodsTypeAdapter
import com.xdys.yhyg.adapte.home.HomeGoodsAdapter
import com.xdys.yhyg.databinding.FragmentOtherBinding
import com.xdys.yhyg.ui.classify.SingleCategoryActivity
import com.xdys.yhyg.vm.HomeViewModel

class OtherFragment : ViewModelFragment<HomeViewModel, FragmentOtherBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOtherBinding = FragmentOtherBinding.inflate(inflater, container, false)

    override val viewModel: HomeViewModel by activityViewModels()
    private val goodsTypeAdapter by lazy { GoodsTypeAdapter() }
    private val brandSelectionAdapter by lazy { BrandSelectionAdapter() }
    private val goodsAdapter by lazy { HomeGoodsAdapter() }
    private var catId: String? = null


    companion object {
        fun newInstance(position: String) = OtherFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        catId = arguments?.getString(EXTRA_ID)
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
        with(goodsTypeAdapter) {
            setOnItemClickListener { _, _, position ->
                data[position].name?.let {
                    data[position].id?.let { id ->
                        SingleCategoryActivity.start(requireContext(), it, id)
                    }
                }
            }
        }
    }

    override fun initData() {
        catId?.let { viewModel.homeSecCat(it) }
        brandSelectionAdapter.setNewInstance(mutableListOf("", "", "", "", "", ""))
        goodsAdapter.setDiffNewData(mutableListOf())
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.secCarLiveData.observe(this) {
            goodsTypeAdapter.setNewInstance(it)
        }
    }
}