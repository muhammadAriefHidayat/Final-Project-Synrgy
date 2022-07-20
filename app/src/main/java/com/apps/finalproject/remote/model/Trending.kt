package com.apps.finalproject.remote.model

import android.os.Parcelable
import com.apps.finalproject.remote.response.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trending(
	val average: Double?,
	val images: List<String>?,
	val isOrganic: Boolean?,
	val variant: List<Variant>?,
	val name: String?,
	val id: String?,
	val brand: Brand?,
): Parcelable

fun Trending.toFavorite() = this.variant?.get(0)?.let {
	FavoriteProduct(
		it.id_entity,
		this.variant[0].id_product,
		this.variant[0].name,
		this.variant[0].price,
		this.images?.get(0) ?: ""
	)
}

fun ListTrendingResponse.toTrending() : Trending {
	return Trending(
		average = this.average,
		images = this.images,
		isOrganic = this.isOrganic,
		variant = this.variant.toListVariant(),
		name = this.name,
		id = this.id,
		brand = this.brand.toBrand()
	)
}


fun List<ListTrendingResponse>.toListTrending() : MutableList<Trending>{
	val listTrendig = mutableListOf<Trending>()
	this.forEach {
		listTrendig.add(it.toTrending()) }
	return listTrendig
}


