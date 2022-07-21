package com.apps.finalproject.remote.response

import com.google.gson.annotations.SerializedName

data class CartResponse(

    @field:SerializedName("data")
    val data: CartResult,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class CartResult(
    @field:SerializedName("id")
    val id: String
)
