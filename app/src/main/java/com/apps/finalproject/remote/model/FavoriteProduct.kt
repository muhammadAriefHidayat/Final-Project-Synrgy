package com.apps.finalproject.remote.model

import com.apps.finalproject.local.entity.Favorite

data class FavoriteProduct(
    val id: Int,
    val id_product: String,
    val productName: String,
    val price: Int,
    val picture: Int
)

fun FavoriteProduct.toFavoriteEntity() = Favorite(
    this.id,
    this.id_product,
    this.productName,
    this.price,
    this.picture
)

fun FavoriteProduct.toVariant() = Variant(
    id_entity = this.id,
    id_product = this.id_product,
    name = this.productName,
    price = this.price ,
    imageIndex = this.picture,
    quantity = 0,
)

fun List<FavoriteProduct>.toListProduct(): MutableList<Variant>{
    val listVariant = mutableListOf<Variant>()
    this.forEach {
        listVariant.add(it.toVariant())
    }
    return listVariant
}