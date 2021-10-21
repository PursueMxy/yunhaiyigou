package com.xdys.yhyg.entity.address

data class AddressEntity(
    val id: String? = null,
    val consigneeName: String? = null,
    val phone: String? = null,
    var provinceName: String? = null,
    var cityName: String? = null,
    var townsName: String? = null,
    var province: String? = null,
    var city: String? = null,
    var towns: String? = null,
    val detailedAddress: String? = null,
    val hasDefault: String? = null,
)
