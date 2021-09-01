package com.xdys.yhyg.ui.classify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.xdys.yhyg.adapte.classify.CateProductAdapter
import com.xdys.yhyg.adapte.classify.ProductSortAdapter
import com.xdys.yhyg.databinding.FragmentCategoryBinding
import com.xdys.yhyg.entity.classify.SortEntity
import com.xdys.yhyg.vm.CateViewModel
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.extension.dp
import com.xdys.library.extension.px
import com.xdys.library.kit.decoration.DividerItemDecoration

class CategoryFragment : ViewModelFragment<CateViewModel, FragmentCategoryBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCategoryBinding.inflate(inflater, container, false)

    override val viewModel: CateViewModel by activityViewModels()

    private val productSortAdapter by lazy { ProductSortAdapter() }

    private val cateProductAdapter by lazy { CateProductAdapter() }

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = CategoryFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(rvSort) {
            adapter = productSortAdapter
        }
        with(rvGoods) {
            adapter = cateProductAdapter
            addItemDecoration(DividerItemDecoration(7.dp, 8.px))
        }

    }

    override fun initData() {
        productSortAdapter.setNewInstance(
            mutableListOf(
                SortEntity(null, "综合推荐"),
                SortEntity(null, "销量"), SortEntity(null, "价格"), SortEntity(null, "筛选")
            )
        )
        cateProductAdapter.setNewInstance(mutableListOf("","","","",""))
    }
}