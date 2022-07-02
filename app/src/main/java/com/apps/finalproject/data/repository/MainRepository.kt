package com.apps.finalproject.data.repository

import com.apps.finalproject.data.datastore.RemoteDataSource
import com.apps.finalproject.model.RegisterBody

class MainRepository (private val remoteDataSource: RemoteDataSource){
    fun register(registerBody: RegisterBody) = remoteDataSource.register(registerBody)
    fun getReview() = remoteDataSource.getReview()
    fun getArticle() = remoteDataSource.getArticle()
    fun getProductTrending() = remoteDataSource.getProductTrending()
}