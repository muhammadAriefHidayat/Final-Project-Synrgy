package com.apps.finalproject.remote.body

import com.apps.finalproject.remote.model.User
import com.google.gson.annotations.SerializedName

data class RegisterBody(
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
