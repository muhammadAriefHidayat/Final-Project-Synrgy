package com.apps.finalproject.remote.model

import com.apps.finalproject.local.entity.Favorite

data class FavoriteProduct(
    val id: Int,
    val id_product: String,
    val productName: String,
    val price: Int,
    val picture: String
)

fun FavoriteProduct.toFavoriteEntity() = Favorite(
    this.id,
    this.id_product,
    this.productName,
    this.price,
    this.picture
)

fun FavoriteProduct.toFavTrending() = Trending(
    average = 0.0,
    images = listOf(this.picture),
    isOrganic = null,
    variant = listOf(Variant(
        id_entity = 0,
        price = this.price,
        name = this.productName,
        id_product = this.id_product,
        imageIndex = 0,
        quantity = 0,
    )),
    name = "",
    id = "",
    brand = null,
)

fun List<FavoriteProduct>.toListProduct(): MutableList<Trending>{
    val listFavTrending = mutableListOf<Trending>()
    this.forEach {
        listFavTrending.add(it.toFavTrending())
    }
    return listFavTrending
}