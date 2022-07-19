package com.apps.finalproject.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apps.finalproject.remote.model.FavoriteProduct

@Entity(tableName = "favorite_product")
class Favorite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "id_product")
    val id_product: String,

    @ColumnInfo(name = "product_name")
    val productName: String,

    @ColumnInfo(name = "price")
    val price: Int = 0,

    @ColumnInfo(name = "picture")
    val picture: Int
)

fun Favorite.toFavorite() = FavoriteProduct(
    this.id,
    this.id_product,
    this.productName,
    this.price,
    this.picture
)

fun List<Favorite>.toListFavorite(): MutableList<FavoriteProduct>{
    val listFavorite = mutableListOf<FavoriteProduct>()
    this.forEach {
        listFavorite.add(it.toFavorite())
    }
    return listFavorite
}
