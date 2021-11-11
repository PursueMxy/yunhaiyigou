package com.xdys.yhyg.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.home.SearchFoundAdapter
import com.xdys.yhyg.databinding.ActivitySearchBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.dp
import com.xdys.library.extension.px
import com.xdys.library.extension.singleTop
import com.xdys.library.kit.decoration.DividerItemDecoration
import com.xdys.yhyg.adapte.home.SecondsKillAdapter
import com.xdys.yhyg.adapte.home.VerticalGoodsAdapter

class SearchActivity : ViewModelActivity<MineViewModel, ActivitySearchBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
                .singleTop()
            context.startActivity(intent)
        }
    }

    private val recordsAdapter: BaseQuickAdapter<String, BaseViewHolder> by lazy {
        object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_search_history) {
            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(R.id.tvContent, item)
            }
        }
    }
    private val searchFoundAdapter by lazy { SearchFoundAdapter() }

    private val mAdapter by lazy { SecondsKillAdapter() }

    private val verticalAdapter by lazy { VerticalGoodsAdapter() }

    override fun createBinding(): ActivitySearchBinding =
        ActivitySearchBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    override fun initUI(savedInstanceState: Bundle?): Unit = with(binding) {
        with(rvSearchRecords) {
            layoutManager = FlexboxLayoutManager(this@SearchActivity)
            adapter = recordsAdapter
        }
        with(rvSearchFound) {
            adapter = searchFoundAdapter
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(DividerItemDecoration(6.px, 15.px))
        }
        with(rvGoods) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
        with(rvGoodsVer){
            adapter = verticalAdapter
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(DividerItemDecoration(0.dp, 11.px))
        }

        ivBack.setOnClickListener { onBackPressed() }
        etSearch.doAfterTextChanged {
            // 判断是显示历史记录,还是进行搜索
            if (it.isNullOrEmpty()) {
                tvPopularSearches.visibility = View.VISIBLE
                rvSearchFound.visibility = View.VISIBLE
                rvSearchRecords.visibility = View.VISIBLE
                tvSearchRecords.visibility = View.VISIBLE
                viewSearch.visibility = View.VISIBLE
                rvGoods.visibility = View.GONE
            }
        }
        tvSearch.setOnClickListener {
            if (etSearch.text.isEmpty()) {
                tvPopularSearches.visibility = View.VISIBLE
                rvSearchFound.visibility = View.VISIBLE
                rvSearchRecords.visibility = View.VISIBLE
                tvSearchRecords.visibility = View.VISIBLE
                viewSearch.visibility = View.VISIBLE
                rvGoods.visibility = View.GONE
                refreshLayout.visibility = View.GONE
            } else {
                tvPopularSearches.visibility = View.GONE
                rvSearchFound.visibility = View.GONE
                rvSearchRecords.visibility = View.GONE
                tvSearchRecords.visibility = View.GONE
                viewSearch.visibility = View.GONE
                rvGoods.visibility = View.VISIBLE
                refreshLayout.visibility = View.VISIBLE
            }
        }
        with(mAdapter){
            setEmptyView(R.layout.layout_empty_goods)
        }
        with(verticalAdapter){
            setEmptyView(R.layout.layout_empty_goods)
        }
    }

    override fun initData() {
        recordsAdapter.setNewInstance(
            mutableListOf(
                "连衣裙",
                "镜子全身穿衣镜",
                "衬衫",
                "墨镜",
                "棒棒糖",
                "葫芦娃",
                "镜子全身穿衣镜"
            )
        )
        searchFoundAdapter.setNewInstance(
            mutableListOf(
                "连衣裙", "镜子全身穿衣镜", "衬衫", "墨镜", "棒棒糖"
            )
        )
        mAdapter.setNewInstance(mutableListOf())
        verticalAdapter.setNewInstance(mutableListOf())
    }
}