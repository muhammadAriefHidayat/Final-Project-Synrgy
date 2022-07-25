package com.apps.finalproject.data.datastore

import android.util.Log
import com.apps.finalproject.data.api.ApiServices
import com.apps.finalproject.remote.*
import com.apps.finalproject.remote.body.LoginBody
import com.apps.finalproject.remote.body.PaymentBody
import com.apps.finalproject.remote.body.PengirimanBody
import com.apps.finalproject.remote.body.RegisterBody
import com.apps.finalproject.remote.model.*
import com.apps.finalproject.remote.response.ProductsItemResponse
import com.apps.finalproject.utils.AppPref
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
        emit(apiServices.addCart("Bearer ${AppPref.token}",cart))
    }.catch {
        Log.d("cart", "cart: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun getCart() = flow  {
       emit(apiServices.getCart("Bearer ${AppPref.token}"))
    }.catch {
        Log.d("cart", "getCart: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)


    fun getOrders() = flow  {
        emit(apiServices.getOrders("Bearer ${AppPref.token}"))
    }.catch {
        Log.d("orders", "orders: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)


    fun postPayment(paymentBody: PaymentBody) = flow {
        emit(apiServices.payment("Bearer ${AppPref.token}",paymentBody))
    }.catch {
        Log.d("payment", "ongkir: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun postOngkir(pengirimanBody: PengirimanBody) = flow {
        emit(apiServices.postOngkir(pengirimanBody))
    }.catch {
        Log.d("ongkir", "ongkir: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun getProductTrending() = flow<List<Trending>> {
        apiServices.getTrending().data.let {
            emit(it.toListTrending())
        }
    }.catch {
        Log.d("TAG", "getProductTrending: failed = ${it.message}")
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


    fun getDetailTrending(productId: String) = flow {
        emit(apiServices.getDetailTrending(productId).toTrending())
    }.catch {
        Log.d("TAG", "getDetailProduct: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun searchProductByName(name: String) = flow<List<ProductsItemResponse>> {
        apiServices.searchProduct(name, 1, 10).data.products.let {
            emit(it)
        }
    }.catch {
        Log.d("TAG", "getSearchProductResult: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun getAllProduct() = flow<List<Trending>> {
        apiServices.getAllProduct().data.let {
            emit(it.toListTrending())
        }
    }.catch {
        Log.d("TAG", "getAllProduct: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)
}
