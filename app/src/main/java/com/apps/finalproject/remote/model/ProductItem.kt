package com.apps.finalproject.remote.model

import android.os.Parcelable
import com.apps.finalproject.remote.response.ProductsItemResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsItem(
	val images: List<String>,
	val isOrganic: Boolean,
	val variant: List<Variant>,
	val name: String,
	val id: String,
	val brand: Brand
) : Parcelable

fun ProductsItemResponse.toProductItem() : ProductsItem {
	return ProductsItem(
		images = this.images,
		isOrganic = this.isOrganic,
		variant = listOf(
			Variant(
				id_entity = 0,
				price = 0,
				name = "",
				id_product = "",
				imageIndex = 0,
				quantity = 0,
			)
		),
		name = this.name,
		id = this.id,
		brand = Brand(
				name = "",
				description = "",
				logo = "",
				banner = "",
				id = ""
			)
	)
}

fun List<ProductsItemResponse>.toListProductItem() : MutableList<ProductsItem>{
	val listProductItem = mutableListOf<ProductsItem>()
	this.forEach { 
		listProductItem.add(it.toProductItem())
	}
	return listProductItem
}

