package com.apps.finalproject.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart(
    @SerializedName("Authorization")
    var token: String,
    @SerializedName("quantity")
    var quantity: String,
    @SerializedName("variantId")
    var variantId: String,
) : Parcelable