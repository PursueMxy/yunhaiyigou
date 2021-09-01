package com.xdys.yhyg.ui.setting

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
import com.xdys.yhyg.databinding.FragmentApplyLogoutBinding
import com.xdys.yhyg.vm.MineViewModel

class ApplyLogoutFragment : ViewModelFragment<MineViewModel, FragmentApplyLogoutBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentApplyLogoutBinding.inflate(inflater, container, false)

    private val navController by lazy { findNavController() }

    override val viewModel: MineViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvApplyCancellation.setOnClickListener {
            navController.navigate(R.id.logoutValidationFragment)
        }
        with(tvLoginAgree) {
            movementMethod = ClickMovementMethod.getInstance()
            text = buildSpannedString {
                append(getString(R.string.merchant_settlement_agreement_content))
                inSpans(CustomClickSpan({
                }, R.color.BL0099)) { append(getString(R.string.merchant_settlement_agreement)) }
            }
        }
    }
}