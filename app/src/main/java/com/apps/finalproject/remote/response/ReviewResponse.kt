package com.apps.finalproject.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse(

	@field:SerializedName("data")
	val data: List<ReviewUserResponse>,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class UserReviewResponse(

	@field:SerializedName("skinType")
	val skinType: String,


	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("avatar")
	val avatar: String
)

data class ReviewUserResponse(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("effective")
	val effective: Int,

	@field:SerializedName("averageStar")
	val averageStar: Double,

	@field:SerializedName("images")
	val images: List<String>,

	@field:SerializedName("productId")
	val productId: String,

	@field:SerializedName("texture")
	val texture: Int,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("imagesCount")
	val imagesCount: Int,

	@field:SerializedName("packaging")
	val packaging: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("user")
	val user: UserReviewResponse,

	@field:SerializedName("content")
	val content: String
)
