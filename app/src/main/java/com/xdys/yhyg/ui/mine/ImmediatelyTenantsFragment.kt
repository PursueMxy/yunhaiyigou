package com.xdys.yhyg.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.fragment.app.viewModels
import com.xdys.library.base.ViewModelFragment
import com.xdys.library.kit.span.ClickMovementMethod
import com.xdys.library.kit.span.CustomClickSpan
import com.xdys.yhyg.R
import com.xdys.yhyg.databinding.FragmentImmediatelyTenantsBinding
import com.xdys.yhyg.vm.MineViewModel

class ImmediatelyTenantsFragment :
    ViewModelFragment<MineViewModel, FragmentImmediatelyTenantsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentImmediatelyTenantsBinding.inflate(inflater, container, false)

    override val viewModel: MineViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        with(tvAgree) {
            movementMethod = com.xdys.library.kit.span.ClickMovementMethod.getInstance()
            text = buildSpannedString {
                append(getString(R.string.logout_agree))
                inSpans(CustomClickSpan({
                }, R.color.BL0099)) { append(getString(R.string.account_cancellation_agreement)) }
            }
        }
    }
}