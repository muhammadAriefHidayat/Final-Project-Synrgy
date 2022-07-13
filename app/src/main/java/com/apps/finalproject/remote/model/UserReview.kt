package com.apps.finalproject.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserReview(
	val data: List<DataItem>,
	val success: Boolean,
	val message: String,
	val status: String
) : Parcelable

@Parcelize
data class User(
	val skinType: String,
	val address: Any,
	val phone: String,
	val name: String,
	val id: String,
	val avatar: String
) : Parcelable

@Parcelize
data class DataItem(
	val date: String,
	val effective: Int,
	val averageStar: Int,
	val images: List<String>,
	val productId: String,
	val texture: Int,
	val price: Int,
	val imagesCount: Int,
	val packaging: Int,
	val id: String,
	val user: User,
	val content: String
) : Parcelable
