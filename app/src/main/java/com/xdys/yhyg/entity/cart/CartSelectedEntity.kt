package com.xdys.yhyg.entity.cart

data class CartSelectedEntity(
    var allSelected: Boolean = false,
    var totalPrice: String = "0.0",
    var selectAny: Boolean = false
)