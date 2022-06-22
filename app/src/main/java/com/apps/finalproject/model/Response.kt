package com.apps.finalproject.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("success")
    var success: String,
    @SerializedName("status")
    var status: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("data")
    var data: String?,
)
