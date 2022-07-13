package com.apps.finalproject.remote.response

import com.google.gson.annotations.SerializedName

data class GetCartResponse(

    @field:SerializedName("overview")
    val data: Overview,

    @field:SerializedName("items")
    val items: String,

    @field:SerializedName("message")
    val quantity: String,

    @field:SerializedName("status")
    val status: String
)

data class GetCartResult(
    @field:SerializedName("id")
    val id: String
)
data class Overview(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("promo")
    val promo: String,
    @field:SerializedName("delivery")
    val delivery: String,
    @field:SerializedName("total")
    val total: String,
    @field:SerializedName("weight")
    val weight: String

)
