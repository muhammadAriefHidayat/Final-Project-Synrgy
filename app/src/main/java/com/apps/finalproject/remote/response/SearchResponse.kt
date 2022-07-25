package com.apps.finalproject.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("data")
	val data: DataResponse,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class BrandItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("logo")
	val logo: String,

	@field:SerializedName("banner")
	val banner: String,

	@field:SerializedName("id")
	val id: String
)

data class ProductsItemResponse(

	@field:SerializedName("images")
	val images: List<String>,

	@field:SerializedName("isOrganic")
	val isOrganic: Boolean,

	@field:SerializedName("variant")
	val variant: List<VariantItem>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("brand")
	val brand: BrandItem
)

data class VariantItem(

	@field:SerializedName("quantity")
	val quantity: Int,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("weight")
	val weight: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("imageIndex")
	val imageIndex: Int
)

data class DataResponse(

	@field:SerializedName("numberOfPages")
	val numberOfPages: Int,

	@field:SerializedName("numberOfItems")
	val numberOfItems: Int,

	@field:SerializedName("products")
	val products: List<ProductsItemResponse>
)
