package com.xdys.yhyg.entity.order


data class OrderEntity(
    val id: String? = null,
    val orders_count: String? = null,
    val listOrderItem: MutableList<GoodsEntity> = mutableListOf(),
    val status: String = "0",
    val isPay: String = "0",
    val appraisesStatus: String = "0"
)

data class GoodsEntity(
    val id: String? = null,
    val spuId: String? = null,
    val spuName: String? = null,
    val skuId: String? = null,
    val specInfo: String? = null,
    val quantity: String? = null,
    val salesPrice: String? = null,
    val tenantId: String? = null,
    val freightPrice: String? = null,
    val paymentPrice: String? = null,
    val paymentPointsPrice: String? = null,
    val paymentCouponPrice: String? = null,
    val paymentPoints: String? = null,
    val status: String? = null,
    val isRefund: String? = null,
)


data class OrderDetail(
    val createUserId: String? = null,
    val createTime: String? = null,
    val updateUserId: String? = null,
    val updateTime: String? = null,
    val tenantId: String? = null,
    val id: String? = null,
    val shopId: String? = null,
    val sourceType: String? = null,
    val userId: String? = null,
    val userCode: String? = null,
    val orderNo: String? = null,
    val paymentWay: String? = null,
    val deliveryWay: String? = null,
    val paymentType: String? = null,
    val tradeType: String? = null,
    val isPay: String? = null,
    val name: String? = null,
    val status: String? = null,
    val appraisesStatus: String? = null,
    val freightPrice: String? = null,
    val salesPrice: String? = null,
    val paymentPointsPrice: String? = null,
    val paymentCouponPrice: String? = null,
    val paymentPrice: String? = null,
    val paymentPoints: String? = null,
    val orderType: String? = null,
    val payEndTime: String? = null,
    val listOrderItem: MutableList<GoodsEntity> = mutableListOf()

)


data class OrderAddress(
    val address: String? = null,
    val Name: String? = null,
    val telNum: String? = null
)