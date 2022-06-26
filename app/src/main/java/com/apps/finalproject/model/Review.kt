package com.apps.finalproject.model

import android.os.Parcelable
import com.apps.finalproject.model.response.DataItem
import com.apps.finalproject.model.response.ReviewResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
	val date: String,
	val averageStar: Double,
	val images: List<String>,
	val productId: String,
	val imagesCount: Int,
	val id: String,
	val userId: String,
	val content: String
) : Parcelable

fun DataItem.toReview() : Review {
	return Review(
		date = this.date,
		averageStar = this.averageStar,
		images = this.images,
		productId = this.productId,
		imagesCount = this.imagesCount,
		id = this.id,
		userId = this.userId,
		content = this.content
	)
}

fun List<DataItem>.toListReview(): MutableList<Review>{
	val listReview = mutableListOf<Review>()
	this.forEach { listReview.add(it.toReview()) }
	return listReview
}


