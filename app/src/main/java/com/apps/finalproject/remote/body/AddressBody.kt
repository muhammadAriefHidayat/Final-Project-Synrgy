package com.apps.finalproject.remote.body

import com.google.gson.annotations.SerializedName

data class AddressBody(
	@SerializedName("addressDetail")
	val addressDetail: String,
	@SerializedName("cityId")
	val cityId: String,
	@SerializedName("isDefault")
	val isDefault: Boolean,
	@SerializedName("label")
	val label: String,
	@SerializedName("phone")
	val phone: String,
	@SerializedName("postalCode")
	val postalCode: String,
	@SerializedName("receiver")
	val receiver: String,
)
