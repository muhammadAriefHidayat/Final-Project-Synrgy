package com.apps.finalproject.model.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("registerResult")
	val data: RegisterResult,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class RegisterResult(

	@field:SerializedName("token")
	val token: String
)
