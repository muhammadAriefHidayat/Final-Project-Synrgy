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

fun Trending.toFavorite() =
	this.variant?.get(0).let { it?.let { it1 -> FavoriteProduct(it1.id_entity, it.id_product, it.name, it.price, it.imageIndex ) } }

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


