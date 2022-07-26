package com.apps.finalproject.remote.response

import com.google.gson.annotations.SerializedName

data class VariantsResponse(
    @field:SerializedName("data")
    val data: VariantsData,
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("status")
    val status: String
)

data class VariantsData(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("product")
    val product: VarProd,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("price")
    val price: Int,
    @field:SerializedName("weight")
    val weight: Int,
)

data class VarProd(
    @field:SerializedName("images")
    val images: List<String>,
)
