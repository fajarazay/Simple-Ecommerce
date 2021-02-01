package com.github.fajarazay.simpleecommerce.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("category")
    val categoryResponse: List<CategoryResponse>,
    @SerializedName("productPromo")
    val productPromoResponse: List<ProductPromoResponse>
)