package com.xdys.library.entity

data class PayParametersEntity(
    val paymentType: String? = null,
    val appId: String? = null,
    val mchId: String? = null,
    val prepayId: String? = null,
    val packageStr: String? = null,
    val nonceStr: String? = null,
    val timestamp: String? = null,
    val sign: String? = null,
    val signType: String? = null,
    val timeStampStr: String? = null,
)