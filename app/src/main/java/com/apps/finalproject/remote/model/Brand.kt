package com.apps.finalproject.remote.model

import android.os.Parcelable
import com.apps.finalproject.remote.response.BrandResponse
import com.apps.finalproject.remote.response.ListTrendingResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Brand(
    val name: String,
    val description: String,
    val logo: String,
    val banner: String,
    val id: String
) : Parcelable

fun BrandResponse.toBrand() : Brand {
    return Brand(
        name = this.name,
        description = this.description,
        logo = this.logo,
        banner = this.banner,
        id = this.id
    )
}
