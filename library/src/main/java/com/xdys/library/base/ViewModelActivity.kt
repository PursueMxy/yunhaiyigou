package com.xdys.library.base

import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding

abstract class ViewModelActivity<ViewModel : BaseViewModel, B : ViewBinding> : BaseActivity<B>() {

    abstract val viewModel: ViewModel

    @CallSuper
    open fun initObserver() {
        viewModel.loadingLiveData.observe(this, {
            if (it == null) hideLoading() else showLoading(it)
        })
        viewModel.messageLiveData.observe(this, {
            if (!it.isNullOrBlank()) showMessage(it)
        })
    }

    override fun doBeforeInitUI() {
        super.doBeforeInitUI()
        initObserver()
    }
}