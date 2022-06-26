package com.apps.finalproject.di

import android.content.Context
import com.apps.finalproject.data.api.ApiConfig
import com.apps.finalproject.data.datastore.RemoteDataSource
import com.apps.finalproject.data.repository.MainRepository

object Injection {
    fun provideRepository(context: Context): MainRepository {
        val apiServices = ApiConfig.getApiService()
        val remoteDataSource = RemoteDataSource(apiServices)
        return MainRepository(remoteDataSource)
    }
}