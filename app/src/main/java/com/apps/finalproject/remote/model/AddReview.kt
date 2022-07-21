package com.apps.finalproject.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddReview(
    val name: String,
    val description: String,
    val logo: String,
    val banner: String,
    val id: String
) : Parcelable