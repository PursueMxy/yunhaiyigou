package com.xdys.library.event

class ReLoginEvent

/**
 * 商品在购物车中数量变动通知事件
 */
data class ProductCartChangeEvent(val goodsId: String?, val num: Int)

/**
 * 用户登录事件
 */
class TokenChangeEvent(val isNewUser: Boolean)

/**
 * 菜谱收藏变动事件
 */
class RecipeCollectEvent(val cookbookId: String)

/**
 * 余额变动事件
 */
class BalanceChangeEvent

/**
 * 订单删除事件
 */
class OrderDeleteEvent(val orderId: String)

/**
 * 订单状态变化事件
 * @param orderStatus 状态，为空则为未知
 */
class OrderStatusChangeEvent(val orderId: String, val orderStatus: Int?)