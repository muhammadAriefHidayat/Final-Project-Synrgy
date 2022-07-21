package com.apps.finalproject.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart(
    @SerializedName("quantity")
    var quantity: Int,
    @SerializedName("variantId")
    var variantId: String,
) : Parcelable