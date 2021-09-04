package com.xdys.yhyg.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.kit.span.ClickMovementMethod
import com.xdys.library.kit.span.CustomClickSpan
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentRegisterBinding
import com.xdys.yhyg.vm.LoginViewModel

class RegisterFragment : ViewModelFragment<LoginViewModel, FragmentRegisterBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override val viewModel: LoginViewModel by activityViewModels()

    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(tvLoginAgree) {
            movementMethod = ClickMovementMethod.getInstance()
            text = buildSpannedString {
                append(getString(R.string.privacy_agree))
                inSpans(CustomClickSpan({
                }, R.color.BL0099)) { append(getString(R.string.privacy_policy)) }
            }
        }

        tvAlreadyLogged.setOnClickListener {
            navController.navigate(R.id.loginFragment)
        }
    }
}