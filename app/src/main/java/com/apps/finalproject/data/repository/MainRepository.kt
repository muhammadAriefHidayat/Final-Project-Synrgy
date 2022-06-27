package com.apps.finalproject.data.repository

import com.apps.finalproject.data.datastore.RemoteDataSource
import com.apps.finalproject.model.RegisterBody

class MainRepository (private val remoteDataSource: RemoteDataSource){
    fun getReview(userId: String) = remoteDataSource.getReview(userId)
    fun register(registerBody: RegisterBody) = remoteDataSource.register(registerBody)
}