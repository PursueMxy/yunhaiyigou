package com.xdys.yhyg.ui.classify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.xdys.yhyg.adapte.classify.CateLeftAdapter
import com.xdys.yhyg.adapte.classify.CateRightAdapter
import com.xdys.yhyg.databinding.FragmentClassificationBinding
import com.xdys.yhyg.entity.classify.CateEntity
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelFragment

class ClassificationFragment : ViewModelFragment<MineViewModel, FragmentClassificationBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentClassificationBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by viewModels()

    // 左侧
    private val cateAdapter = CateLeftAdapter { binding.pager.setCurrentItem(it, true) }

    // 右侧
    private val cateSecondAdapter by lazy { CateRightAdapter(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        with(rvLeft) {
            adapter = cateAdapter
        }
        // 右侧分类
        with(pager) {
            orientation = ViewPager2.ORIENTATION_VERTICAL
            adapter = cateSecondAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    cateAdapter.scrollToPosition(position)
                }
            })
        }
    }

    override fun initData() {
        super.initData()
        cateAdapter.setNewInstance(mutableListOf("推荐", "男装", "女装", "美食", "电器", "酒水", "家居日用"))
        cateSecondAdapter.list = mutableListOf(
            CateEntity(), CateEntity(), CateEntity(), CateEntity(),
            CateEntity(), CateEntity(), CateEntity(),
        )
    }

    override fun initObserver() {
        super.initObserver()
    }
}