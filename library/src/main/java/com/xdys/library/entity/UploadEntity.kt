package com.xdys.library.entity

data class UploadEntity(
    val imgPath: String? = null,
    val imgUrl: String? = null
) {
    val isEmpty: Boolean
        get() = imgPath.isNullOrBlank() && imgUrl.isNullOrBlank()
}

data class InUploadEntity(
    val url: String? = null,
    val full_url: MutableList<String>? = null,
    val name: MutableList<String>? = null
)
