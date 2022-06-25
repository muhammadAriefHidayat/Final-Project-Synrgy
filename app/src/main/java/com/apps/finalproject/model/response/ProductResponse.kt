package com.apps.finalproject.model.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataItem(

	@field:SerializedName("quantity")
	val quantity: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("variant")
	val variant: List<VariantItem>,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("brand")
	val brand: BrandResponse
)

data class BrandResponse(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("banner")
	val banner: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("logo")
	val logo: String,

	@field:SerializedName("id")
	val id: String
)

data class VariantItem(

	@field:SerializedName("quantity")
	val quantity: Int,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String
)
