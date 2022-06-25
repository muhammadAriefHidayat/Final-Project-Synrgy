package com.apps.finalproject.data.datastore

import android.util.Log
import com.apps.finalproject.data.api.ApiServices
import com.apps.finalproject.model.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiServices: ApiServices) {

    fun getReview(userId: String) = flow {
        emit(apiServices.getReview(userId).data)
    }.catch {
        Log.d("TAG", "getReview: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)
}