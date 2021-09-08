package com.xdys.library.network.base

import com.xdys.library.entity.InUploadEntity
import okhttp3.MultipartBody
import retrofit2.http.*

interface BaseApi {

    @Multipart
    @POST("common/upload")
    suspend fun uploadPicture(@Part list: List<MultipartBody.Part>): Result<InUploadEntity>

    @FormUrlEncoded
    @POST("cart/add")
    suspend fun addCart(
        @Field("type") type: Int = 1,
        @Field("goods_id") goodsId: String,
        @Field("goods_num") goodsNum: Int? = null,
        @Field("active_id") activeId: String? = null,
        @Field("is_newcomer") isNewbie: Int? = null
    ): BaseResult

}