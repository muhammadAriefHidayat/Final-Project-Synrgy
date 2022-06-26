package com.apps.finalproject.model.response

import com.apps.finalproject.model.Review
import com.google.gson.annotations.SerializedName

data class ReviewResponse(

	@field:SerializedName("data")
	val data: List<Review>,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("averageStar")
	val averageStar: Double,

	@field:SerializedName("effective")
	val effective: Int,

	@field:SerializedName("images")
	val images: List<String>,

	@field:SerializedName("productId")
	val productId: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("texture")
	val texture: Int,

	@field:SerializedName("imagesCount")
	val imagesCount: Int,

	@field:SerializedName("packaging")
	val packaging: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("content")
	val content: String
)
