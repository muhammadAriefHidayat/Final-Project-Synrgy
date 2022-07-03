package com.apps.finalproject.model

import android.os.Parcelable
import com.apps.finalproject.model.response.*
import com.apps.finalproject.model.response.Brand
import kotlinx.parcelize.Parcelize


data class Trending(
	val average: Double,
	val images: List<String>,
	val isOrganic: Boolean,
	val variant: List<ListVariantResponse>,
	val name: String,
	val id: String,
	val brand: Brand
)

fun ListTrendingResponse.toTrending() : Trending {
	return Trending(
		average = this.average,
		images = this.images,
		isOrganic = this.isOrganic,
		variant = this.variant,
		name = this.name,
		id = this.id,
		brand = this.brand
	)
}


fun List<ListTrendingResponse>.toListTrending() : MutableList<Trending>{
	val listTrendig = mutableListOf<Trending>()
	this.forEach { listTrendig.add(it.toTrending()) }
	return listTrendig
}


