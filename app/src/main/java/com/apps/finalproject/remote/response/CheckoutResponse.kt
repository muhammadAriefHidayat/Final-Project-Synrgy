package com.apps.finalproject.remote.response

import com.google.gson.annotations.SerializedName

data class PaymentResponse(

	@field:SerializedName("data")
	val data: dataPayment,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class dataPayment(

	@field:SerializedName("bankName")
	val bankName: String
)
