package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemLongClickListener
import com.hjq.toast.ToastUtils
import com.xdys.yhyg.adapte.mine.CommodityCollectionAdapter
import com.xdys.yhyg.databinding.FragmentCommodityCollectionBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment
import com.xdys.yhyg.R
import com.xdys.yhyg.ui.goods.GoodsDetailActivity

class CommodityCollectionFragment :
    ViewModelFragment<MineViewModel, FragmentCommodityCollectionBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCommodityCollectionBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by activityViewModels()

    companion object {
        private val EXTRA_ID = "position"
        fun newInstance(position: Int) = CommodityCollectionFragment().apply {
            arguments = bundleOf(EXTRA_ID to position)
        }
    }

    private val mAdapter by lazy { CommodityCollectionAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        with(rvCommodity) {
            adapter = mAdapter
        }
        with(mAdapter) {
            setOnItemClickListener { _, _, position ->
                data[position].goodsSpuApiVo?.id?.let {
                    GoodsDetailActivity.start(requireContext(), it)
                }
            }
            setOnItemChildClickListener { _, view, position ->
                if (view.id == R.id.cbCartCheck) changeProductSelectStatus(position)
            }
            setOnItemLongClickListener(object : OnItemLongClickListener {
                override fun onItemLongClick(
                    adapter: BaseQuickAdapter<*, *>,
                    view: View,
                    position: Int
                ): Boolean {
                    data[position].id?.let { viewModel.collect(it) }
                    return true
                }
            })
        }
        tvCollectionCheckAll.setOnClickListener {
            it.isSelected = !it.isSelected
            mAdapter.changeAllProductStatus(it.isSelected)
        }
        tvCancelCollection.setOnClickListener {
            mAdapter.collectionParamsLiveData.value?.let {
                viewModel.collect(it.cartIds)
            }
        }
        refreshLayout.setOnRefreshListener {
            viewModel.collectList()
            binding.refreshLayout.finishRefresh()
        }
    }

    override fun initData() {
        viewModel.collectList()
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.collectListLivaData.observe(this) {
            binding.refreshLayout.finishRefresh()
            mAdapter.setDiffNewData(it)
        }
        viewModel.updateEditLiveData.observe(this) {
            mAdapter.changeProductStatus(it)
            binding.viewCollectionBottom.isVisible = it
        }
    }
}