package com.xdys.yhyg.vm

import androidx.lifecycle.MutableLiveData
import com.xdys.library.base.BaseViewModel

class HomeViewModel : BaseViewModel() {
    val conversionLiveData by lazy { MutableLiveData<Boolean>() }

    fun conversion(choose: Boolean) {
        conversionLiveData.postValue(choose)
    }
}