package com.apps.finalproject.remote.body

import com.google.gson.annotations.SerializedName

data class PaymentBody(
	@SerializedName("bank")
	val bank: String,
	@SerializedName("delivery")
	val delivery: String,
	@SerializedName("deliveryService")
	val deliveryService: String,
	@SerializedName("paymentType")
	val paymentType: String
)
