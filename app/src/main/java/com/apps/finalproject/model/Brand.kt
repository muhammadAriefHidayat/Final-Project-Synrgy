package com.apps.finalproject.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Brand(
    val name: String,
    val description: String,
    val logo: String,
    val banner: String,
    val id: String
) : Parcelable