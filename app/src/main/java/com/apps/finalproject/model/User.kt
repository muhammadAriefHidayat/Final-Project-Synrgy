package com.apps.finalproject.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    var name: String,
    @SerializedName("skinType")
    var skinType: String?,
)