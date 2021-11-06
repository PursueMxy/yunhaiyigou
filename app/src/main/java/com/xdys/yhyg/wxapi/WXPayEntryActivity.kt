package com.xdys.yhyg.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.hjq.toast.ToastUtils
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelpay.PayResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.xdys.library.event.LiveDataBus
import com.xdys.library.event.PaySuccessEvent
import com.xdys.library.utils.ThirdUtils
import com.xdys.yhyg.R

class WXPayEntryActivity : Activity(), IWXAPIEventHandler {

    companion object {
        private val TAG = WXPayEntryActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThirdUtils.api.handleIntent(intent, this)
    }

    override fun onResume() {
        super.onResume()
        finish()
    }

    override fun onResp(resp: BaseResp?) {
        if (resp != null && resp.type == ConstantsAPI.COMMAND_PAY_BY_WX) (resp as? PayResp)?.let {
            when (it.errCode) {
                // 支付成功
                BaseResp.ErrCode.ERR_OK -> LiveDataBus.post(PaySuccessEvent(true))
                // 用户取消
                BaseResp.ErrCode.ERR_USER_CANCEL -> ToastUtils.show(R.string.pay_cancel)
                // 支付失败
                else -> LiveDataBus.post(PaySuccessEvent(false))
            }
        }
        finish()
    }

    override fun onReq(baseReq: BaseReq?) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}