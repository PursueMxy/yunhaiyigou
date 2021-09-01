package com.xdys.library.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.xdys.library.R

abstract class BaseCenteredDialog<B : ViewBinding>( mContext: Context) : Dialog(
    mContext, R.style.color_dialog
) {
    protected abstract fun init()
    protected lateinit var binding: B
    abstract fun createBinding(): B
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createBinding()
        setContentView(binding.root)
        init()
    }

    override fun onStop() {
        super.onStop()
        dismiss()
    }
}