package com.github.fajarazay.simpleecommerce.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ProductItemResponse(
    @SerializedName("data")
    val dataResponse: DataResponse
)