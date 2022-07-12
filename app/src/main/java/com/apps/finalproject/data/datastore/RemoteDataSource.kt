package com.apps.finalproject.data.datastore

import android.util.Log
import com.apps.finalproject.data.api.ApiServices
import com.apps.finalproject.remote.*
import com.apps.finalproject.remote.body.LoginBody
import com.apps.finalproject.remote.body.RegisterBody
import com.apps.finalproject.remote.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiServices: ApiServices) {

    fun login(loginBody: LoginBody) = flow {
        emit(apiServices.login(loginBody))
    }.catch {
        Log.d("login", "login: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun register(registerBody: RegisterBody) = flow {
        emit(apiServices.register(registerBody))
    }.catch {
        Log.d("register", "register: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)


    fun addCart(cart: Cart) = flow {
        emit(apiServices.addCart(cart.token,cart.quantity,cart.variantId))
    }.catch {
        Log.d("login", "login: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun getReview() = flow<List<Review>> {
        apiServices.getReview().data.let {
            emit(it.toListReview())
        }
    }.catch {
        Log.d("TAG", "getReview: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun getArticle() = flow<List<Article>> {
        apiServices.getArticle().data.let {
            emit(it.toListArticle())
        }
    }.catch {
        Log.d("TAG", "getArticle: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun getProductTrending() = flow<List<Trending>> {
        apiServices.getTrending().data.let {
            emit(it.toListTrending())
        }
    }.catch {
        Log.d("TAG", "getProductTrending: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun getDetailTrending(productId: String) = flow {
        emit(apiServices.getDetailTrending(productId).toTrending())
    }.catch {
        Log.d("TAG", "getDetailProduct: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)
}
