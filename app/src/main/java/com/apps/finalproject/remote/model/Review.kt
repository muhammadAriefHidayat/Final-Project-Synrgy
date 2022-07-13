package com.apps.finalproject.remote.model

import android.os.Parcelable
import com.apps.finalproject.remote.response.ReviewUserResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
	val date: String,
	val effective: Int,
	val averageStar: Double,
	val images: List<String>,
	val productId: String,
	val texture: Int,
	val price: Int,
	val imagesCount: Int,
	val packaging: Int,
	val id: String,
	val user: UserReview,
	val content: String
) : Parcelable

fun ReviewUserResponse.toReview() : Review {
	return Review(
		date = this.date,
		effective = this.effective,
		averageStar = this.averageStar,
		images = this.images,
		productId = this.productId,
		texture = this.texture,
		price = this.price,
		imagesCount = this.imagesCount,
		packaging = this.packaging,
		id = this.id,
		user = this.user.toUser(),
		content = this.content
	)
}

fun List<ReviewUserResponse>.toListReview(): MutableList<Review>{
	val listReview = mutableListOf<Review>()
	this.forEach { listReview.add(it.toReview()) }
	return listReview
}


