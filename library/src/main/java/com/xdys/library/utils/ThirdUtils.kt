package com.xdys.library.utils

import android.app.Activity
import com.alipay.sdk.app.PayTask
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.xdys.library.config.Constant
import com.xdys.library.extension.context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ThirdUtils {

    val api by lazy { WXAPIFactory.createWXAPI(context, Constant.Config.WX_APPID) }

    suspend fun aliPay(activity: Activity, params: String): AliResult {
        return withContext(Dispatchers.IO) {
            AliResult(PayTask(activity).payV2(params, true))
        }
    }

    fun wxPay(map: Map<String, *>): Boolean {
        return if (api.isWXAppInstalled) {
            api.registerApp(Constant.Config.WX_APPID)
            api.sendReq(PayReq().apply {
                appId = map["appid"] as String
                partnerId = map["partnerid"] as String
                prepayId = map["prepayid"] as String
                nonceStr = map["noncestr"] as String
                timeStamp = map["timestamp"].toString()
                packageValue = map["package"] as String
                sign = map["sign"] as String
            })
            true
        } else false
    }

    fun wxAuth() {
        val req = SendAuth.Req().apply { scope = "snsapi_userinfo" }
        api.sendReq(req)
    }
}

class AliResult(private val map: Map<String, String>) {

    private val successCode = "9000"

    val resultStatus: String
        get() = map["resultStatus"] ?: ""
    val result: String
        get() = map["result"] ?: ""
    val memo: String
        get() = map["memo"] ?: ""

    override fun toString(): String {
        return "Result{resultStatus : $resultStatus,result : $result,memo : $memo}"
    }

    val success
        get() = resultStatus == successCode
}