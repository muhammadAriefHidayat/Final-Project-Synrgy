package com.apps.finalproject.data.repository

import com.apps.finalproject.data.datastore.LocalDataSource
import com.apps.finalproject.data.datastore.RemoteDataSource
import com.apps.finalproject.remote.body.LoginBody
import com.apps.finalproject.remote.body.RegisterBody
import com.apps.finalproject.remote.model.Cart
import com.apps.finalproject.remote.model.FavoriteProduct
import com.apps.finalproject.remote.model.toFavoriteEntity

class MainRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ){

    fun register(registerBody: RegisterBody) = remoteDataSource.register(registerBody)
    fun login(loginBody: LoginBody) = remoteDataSource.login(loginBody)
    fun getReview() = remoteDataSource.getReview()
    fun getArticle() = remoteDataSource.getArticle()
    fun addCart(cart: Cart) = remoteDataSource.addCart(cart)
    fun getProductTrending() = remoteDataSource.getProductTrending()
    fun getDetailTrending(productId: String) = remoteDataSource.getDetailTrending(productId)
    fun searchProductByName(name: String) = remoteDataSource.searchProductByName(name)


    fun getAllFavorite() = localDataSource.getAllFavorite()
    fun getMyFavorite(productName: String) = localDataSource.getMyFavorite(productName)

    suspend fun addFavorite(favoriteProduct: FavoriteProduct) =
        localDataSource.insertFavorite(favoriteProduct.toFavoriteEntity())

    suspend fun deleteFavorite(favoriteProduct: FavoriteProduct) =
        localDataSource.deleteFavorite(favoriteProduct.toFavoriteEntity())
}