package com.xdys.yhyg.ui.mine

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.ActivityAddressBinding
import com.xdys.yhyg.vm.MineViewModel
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.config.Constant
import com.xdys.library.extension.newTask
import com.xdys.library.extension.singleTop

class AddressActivity : ViewModelActivity<MineViewModel, ActivityAddressBinding>() {

    companion object {
        fun select(activity: Activity, requestCode: Int) {
            val intent = Intent(activity, AddressActivity::class.java)
                .putExtra(Constant.Key.EXTRA_DATA, true)
                .singleTop()
            activity.startActivityForResult(intent, requestCode)
        }

        fun edit(context: Context) {
            val intent = Intent(context, AddressActivity::class.java)
                .putExtra(Constant.Key.EXTRA_DATA, false)
                .singleTop()
            if (context !is Activity) intent.newTask()
            context.startActivity(intent)
        }
    }

    override fun createBinding() = ActivityAddressBinding.inflate(layoutInflater)

    override val viewModel: MineViewModel by viewModels()

    private val navController by lazy { findNavController(R.id.addressContainer) }
}