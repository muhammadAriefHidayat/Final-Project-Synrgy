package com.apps.finalproject.model

import android.os.Parcelable
import com.apps.finalproject.model.response.BrandResponse
import com.apps.finalproject.model.response.ProductResponse
import com.apps.finalproject.model.response.VariantItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VariantTrend(
	val price: Int,
	val name: String,
) : Parcelable

@Parcelize
data class BrandTrend(
	val name: String,
	val banner: String,
) : Parcelable

fun BrandResponse.BrandTrend() : BrandTrend {
	return BrandTrend(
		name = this.name,
		banner = this.banner
	)
}

fun VariantItem.VarianTrend() : VariantTrend{
	return VariantTrend(
		name = this.name,
		price = this.price
	)
}