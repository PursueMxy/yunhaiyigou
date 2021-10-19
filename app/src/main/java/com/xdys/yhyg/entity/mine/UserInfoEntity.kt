package com.xdys.yhyg.entity.mine

data class UserInfoEntity(
    val id: String? = null,
    val nickName: String? = null,
    val sex: String? = "2",
    val headimgUrl: String? = null,
    val avatar: String? = null,
    val userCode: String? = null,
    val phone: String? = null,
    val province: String? = "",
    val city: String? = "",
    val towns: String? = "",
    val isMerchant: String? = "0",
    val onlineIntegral: String? = "0",
    val sumOnlineIntegral: String? = "0",
    val freezeOnlineIntegral: String? = "0",
    val offlineIntegral: String? = "0",
    val sumOfflineIntegral: String? = "0",
    val freezeOfflineIntegral: String? = "0",
    val whetherCertification: String? = "0",
    val whetherPassword: String? = "0",
    val whetherLogin: String? = "0",
    val inviteCode: String? = "0",
    val balance: String? = "0",
    val cardVoucherList: MutableList<Any> = mutableListOf()

)