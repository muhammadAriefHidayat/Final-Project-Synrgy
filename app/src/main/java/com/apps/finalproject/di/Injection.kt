package com.apps.finalproject.di

import android.content.Context
import com.apps.finalproject.data.api.ApiConfig
import com.apps.finalproject.data.datastore.LocalDataSource
import com.apps.finalproject.data.datastore.RemoteDataSource
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.local.room.FavoriteDb

object Injection {
    fun provideRepository(context: Context): MainRepository {
        val apiServices = ApiConfig.getApiService()
        val database = FavoriteDb.getInstance(context)
        val dao = database.favoriteDao()
        val remoteDataSource = RemoteDataSource(apiServices)
        val localDataSource = LocalDataSource(dao)
        return MainRepository(remoteDataSource, localDataSource)
    }
}