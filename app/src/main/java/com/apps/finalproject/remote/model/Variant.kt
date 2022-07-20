package com.apps.finalproject.remote.model

import android.os.Parcelable
import com.apps.finalproject.remote.response.ListVariantResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Variant(
    val id_entity: Int,
    val price: Int,
    val name: String,
    val id_product: String,
    val imageIndex: Int,
    val quantity: Int
): Parcelable

fun ListVariantResponse.toVariant() : Variant {
    return Variant(
        id_entity = this.quantity,
        price = this.price ,
        name = this.name,
        id_product = this.id,
        imageIndex = this.imageIndex,
        quantity = this.quantity
    )
}


fun List<ListVariantResponse>.toListVariant() : MutableList<Variant>{
    val listVariant = mutableListOf<Variant>()
    this.forEach { listVariant.add(it.toVariant()) }
    return listVariant
}