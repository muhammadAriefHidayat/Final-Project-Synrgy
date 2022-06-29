package com.apps.finalproject.model.response

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("data")
	val data: List<DataArticle>,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class ProductCategoriesItem(

	@field:SerializedName("subChild")
	val subChild: String,

	@field:SerializedName("subCategory")
	val subCategory: Any,

	@field:SerializedName("subCategoryId")
	val subCategoryId: Any,

	@field:SerializedName("subChildId")
	val subChildId: String,

	@field:SerializedName("category")
	val category: Any,

	@field:SerializedName("categoryId")
	val categoryId: Any
)

data class IngredientCategoriesItem(

	@field:SerializedName("ingredientId")
	val ingredientId: String,

	@field:SerializedName("name")
	val name: String
)

data class DataArticle(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("productCategories")
	val productCategories: List<ProductCategoriesItem>,

	@field:SerializedName("ingredientCategories")
	val ingredientCategories: List<IngredientCategoriesItem>,

	@field:SerializedName("photo")
	val photo: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("userid")
	val userid: String,

	@field:SerializedName("content")
	val content: String
)
