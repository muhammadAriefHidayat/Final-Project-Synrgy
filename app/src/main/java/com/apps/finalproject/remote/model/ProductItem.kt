package com.apps.finalproject.remote.model

import android.os.Parcelable
import com.apps.finalproject.remote.response.Brand
import com.apps.finalproject.remote.response.ProductsItemResponse
import com.apps.finalproject.remote.response.VariantItem
import kotlinx.android.parcel.Parcelize


data class ProductsItem(
	val images: List<String>,
	val isOrganic: Boolean,
	val variant: List<VariantItem>,
	val name: String,
	val id: String,
	val brand: Brand
) 

fun ProductsItemResponse.toProductItem() : ProductsItem {
	return ProductsItem(
		images = this.images,
		isOrganic = this.isOrganic,
		variant = this.variant,
		name = this.name,
		id = this.id,
		brand = this.brand
	)
}

fun List<ProductsItemResponse>.toListProductItem() : MutableList<ProductsItem>{
	val listProductItem = mutableListOf<ProductsItem>()
	this.forEach { 
		listProductItem.add(it.toProductItem())
	}
	return listProductItem
}

