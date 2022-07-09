package com.apps.finalproject.remote.model

import android.os.Parcelable
import com.apps.finalproject.remote.response.ListVariantResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Variant(
    val quantity: Int,
    val price: Int,
    val name: String,
    val id: String,
    val imageIndex: Int
): Parcelable

fun ListVariantResponse.toVariant() : Variant {
    return Variant(
        quantity = this.quantity,
        price = this.price ,
        name = this.name,
        id = this.id,
        imageIndex = this.imageIndex
    )
}


fun List<ListVariantResponse>.toListVariant() : MutableList<Variant>{
    val listVariant = mutableListOf<Variant>()
    this.forEach { listVariant.add(it.toVariant()) }
    return listVariant
}