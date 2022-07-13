package com.apps.finalproject.remote.model

import android.os.Parcelable
import com.apps.finalproject.remote.response.ListTrendingResponse
import com.apps.finalproject.remote.response.UserReviewResponse
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserReview(
	val skinType: String,
	val phone: String,
	val name: String,
	val id: String,
	val avatar: String
) : Parcelable

fun UserReviewResponse.toUser() : UserReview {
	return UserReview(
		skinType = this.skinType,
		phone = this.phone,
		name = this.name,
		id = this.id,
		avatar = this.avatar
	)
}

