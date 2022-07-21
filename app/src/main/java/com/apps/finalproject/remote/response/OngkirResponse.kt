package com.apps.finalproject.remote.response

import com.google.gson.annotations.SerializedName

data class OngkirResponse(

    @field:SerializedName("data")
    val data: List<Ekspedisi>,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class Ekspedisi(
    @field:SerializedName("code")
    val code: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("costs")
    val costs: List<Service>
    )

data class Service(
    @field:SerializedName("service")
    val service: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("cost")
    val cost: List<Cost>
    )

data class Cost(
    @field:SerializedName("value")
    val value: Int,
    @field:SerializedName("etd")
    val etd: String,
    @field:SerializedName("note")
    val note: String
    )
