package com.xdys.yhyg.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.home.SearchFoundAdapter
import com.xdys.yhyg.databinding.ActivitySearchBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.px
import com.xdys.library.extension.singleTop
import com.xdys.library.kit.decoration.DividerItemDecoration

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

    override fun createBinding(): ActivitySearchBinding =
        ActivitySearchBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    override fun initUI(savedInstanceState: Bundle?) = with(binding) {
        with(rvSearchRecords) {
            layoutManager = FlexboxLayoutManager(this@SearchActivity)
            adapter = recordsAdapter
        }
        with(rvSearchFound) {
            adapter = searchFoundAdapter
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(DividerItemDecoration(6.px, 15.px))
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
    }
}