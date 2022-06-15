package com.apps.finalproject.model

import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("avatar")
    var avatar: String,
    @SerializedName("email")
    var email: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("role")
    var role: String?,
    @SerializedName("user")
    var user: User?
)
