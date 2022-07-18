package com.apps.finalproject.data.datastore

import android.util.Log
import com.apps.finalproject.local.entity.Favorite
import com.apps.finalproject.local.entity.toListFavorite
import com.apps.finalproject.local.room.FavoriteDao
import com.apps.finalproject.remote.model.FavoriteProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(private val favoriteDao: FavoriteDao) {
    fun getAllFavorite() = flow<List<FavoriteProduct>> {
        favoriteDao.getFavorite().collect{
            emit(it.toListFavorite())
        }
    }

    fun getMyFavorite(productName: String) = flow{
        favoriteDao.getMyFavorite(productName).collect{
            emit(it)
        }
    }.catch {
        Log.d("TAG", "isFavorite: ")
    }.flowOn(Dispatchers.IO)

    suspend fun insertFavorite(favorite: Favorite) =
        favoriteDao.insertFavorite(favorite)

    suspend fun deleteFavorite(favorite: Favorite) =
        favoriteDao.deleteFavorite(favorite)
}