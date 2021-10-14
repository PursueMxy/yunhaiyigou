package com.xdys.yhyg.ui.login

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.xdys.library.base.ViewModelActivity
import com.xdys.library.extension.clearTask
import com.xdys.library.extension.newTask
import com.xdys.library.extension.single
import com.xdys.yhyg.databinding.ActivityLoginBinding
import com.xdys.yhyg.vm.LoginViewModel

class LoginActivity : ViewModelActivity<LoginViewModel, ActivityLoginBinding>() {

    companion object {
        fun startActivity(context: Context, isNew: Boolean = true) {
            val intent = Intent(context, LoginActivity::class.java).single()
            if (isNew) intent.newTask().clearTask()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun createBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override val viewModel: LoginViewModel by viewModels()
}