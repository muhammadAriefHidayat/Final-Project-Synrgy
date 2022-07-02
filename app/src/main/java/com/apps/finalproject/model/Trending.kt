package com.apps.finalproject.model

data class Trending(
	val data: List<DataItem>,
	val success: Boolean,
	val message: String,
	val status: String
)

data class DataItem(
	val average: Int,
	val images: List<String>,
	val isOrganic: Boolean,
	val variant: List<VariantItem>,
	val name: String,
	val id: String,
	val brand: Brand
)

data class Brand(
	val name: String,
	val description: String,
	val logo: String,
	val banner: String,
	val id: String
)

data class VariantItem(
	val quantity: Int,
	val price: Int,
	val name: String,
	val id: String,
	val imageIndex: Int
)

