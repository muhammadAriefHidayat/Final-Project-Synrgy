package com.apps.finalproject.remote.body

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PengirimanBody(
    @SerializedName("cityId")
    var cityId: String,
    @SerializedName("courier")
    var courier: String,
    @SerializedName("destCityId")
    var destCityId: String,
    @SerializedName("weight")
    var weight: Int,
) : Parcelable