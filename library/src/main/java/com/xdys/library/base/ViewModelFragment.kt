package com.xdys.library.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class ViewModelFragment<ViewModel : BaseViewModel, B : ViewBinding> : BaseFragment<B>() {

    abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        initObserver()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    open fun initObserver() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner, {
            if (it == null) hideLoading() else showLoading(it)
        })
        viewModel.messageLiveData.observe(viewLifecycleOwner, {
            if (!it.isNullOrBlank()) showMessage(it)
        })
    }
}