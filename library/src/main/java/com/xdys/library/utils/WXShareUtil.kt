package com.xdys.library.utils

import android.content.Context
import android.graphics.Bitmap
import com.hjq.toast.ToastUtils
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.xdys.library.config.Constant.Config.WX_APPID

object WXShareUtil {
    /**
     * 分享网页类型至微信
     *
     * @param context 上下文
     * @param webUrl  网页的url
     * @param title   网页标题
     * @param content 网页描述
     * @param bitmap  位图
     */
    fun shareWeb(
        context: Context?, webUrl: String?, title: String?,
        content: String?, isSession: Boolean = true
    ) { // 通过appId得到IWXAPI这个对象
        val wxapi = WXAPIFactory.createWXAPI(context, WX_APPID)
        // 检查手机或者模拟器是否安装了微信
        if (!wxapi.isWXAppInstalled) {
            ToastUtils.show("您还没有安装微信")
//            context?.toast("您还没有安装微信")
            return
        }
        // 初始化一个WXWebpageObject对象
        val webpageObject = WXWebpageObject()
        // 填写网页的url
        webpageObject.webpageUrl = webUrl
        // 用WXWebpageObject对象初始化一个WXMediaMessage对象
        val msg = WXMediaMessage(webpageObject)
        // 填写网页标题、描述、位图
        msg.title = title
        msg.description = content
        // 如果没有位图，可以传null，会显示默认的图片
        msg.setThumbImage(null)
        // 构造一个Req
        val req = SendMessageToWX.Req()
        // transaction用于唯一标识一个请求（可自定义）
        req.transaction = "webpage"
        // 上文的WXMediaMessage对象
        req.message = msg
        // SendMessageToWX.Req.WXSceneSession是分享到好友会话
        // SendMessageToWX.Req.WXSceneTimeline是分享到朋友圈
        if (isSession) {
            req.scene = SendMessageToWX.Req.WXSceneSession
        } else {
            req.scene = SendMessageToWX.Req.WXSceneTimeline
        }
        // 向微信发送请求
        wxapi.sendReq(req)
    }

}