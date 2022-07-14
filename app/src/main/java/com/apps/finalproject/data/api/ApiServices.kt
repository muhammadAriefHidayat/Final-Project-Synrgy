package com.apps.finalproject.data.api

import com.apps.finalproject.remote.body.LoginBody
import com.apps.finalproject.remote.body.RegisterBody
import com.apps.finalproject.remote.model.Brand
import com.apps.finalproject.remote.model.Cart
import com.apps.finalproject.remote.model.Review
import com.apps.finalproject.remote.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    @POST("/api/v1/review")
    fun addReview(
        @Body review :Review
    ): Call<LoginResponse>

    @POST("/api/v1/auth/login")
    fun login(
        @Body loginBody : LoginBody
    ): Call<LoginResponse>


    @POST("/api/v1/carts")
    fun addCart(
        @Body cart: Cart
        ):Call<CartResponse>

    @GET("/api/v1/carts")
    fun getCart(
    ):Call<CartResponse>

    @POST("/api/v1/auth/register")
    fun register(
        @Body register: RegisterBody
    ): Call<RegisterResponse>

    @GET("/api/v1/review")
    suspend fun getReview(
    ): ReviewResponse

    @GET("/api/v1/article")
    suspend fun getArticle() : ArticleResponse

    @GET("/api/v1/product/q/trending")
    suspend fun getTrending() : TrendingResponse

    @GET("/api/v1/product/{id}")
    suspend fun getDetailTrending(
        @Path("productId") productId: String
    ): ListTrendingResponse

    @GET("/api/v1/product/p")
    suspend fun searchProduct(
        @Query("q") name: String
    ): TrendingResponse
}