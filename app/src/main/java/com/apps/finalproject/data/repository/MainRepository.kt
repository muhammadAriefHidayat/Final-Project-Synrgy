package com.apps.finalproject.data.repository

import com.apps.finalproject.data.datastore.RemoteDataSource

class MainRepository (private val remoteDataSource: RemoteDataSource){
    fun getReview() = remoteDataSource.getReview()
}