package com.apps.finalproject.local.room

import androidx.room.*
import com.apps.finalproject.local.entity.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_product ORDER BY product_name ASC ")
    fun getFavorite(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite_product WHERE product_name = :product_name LIMIT 1")
    fun getMyFavorite(product_name: String): Flow<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite_product: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite_product: Favorite)
}