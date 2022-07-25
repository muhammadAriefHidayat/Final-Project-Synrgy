package com.apps.finalproject.remote.model

import android.os.Parcelable
import com.apps.finalproject.remote.response.DataResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataProduct(
	val numberOfPages: Int,
	val numberOfItems: Int,
	val products: List<ProductsItem>
) : Parcelable

fun DataResponse.toDataProduct() : DataProduct{
	return DataProduct (
		numberOfPages = 0,
		numberOfItems = 0,
		products = this.products.toListProductItem()
	)
}

fun List<DataResponse>.toListDataProduct() : MutableList<DataProduct>{
	val listDataProduct = mutableListOf<DataProduct>()
	this.forEach {
		listDataProduct.add(it.toDataProduct())
	}
	return listDataProduct
}




