package com.apps.finalproject.data.api

import com.apps.finalproject.remote.body.*
import com.apps.finalproject.remote.model.Cart
import com.apps.finalproject.remote.model.Review
import com.apps.finalproject.remote.model.Token
import com.apps.finalproject.remote.response.*
import com.apps.finalproject.utils.AppPref
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    @POST("/api/v1/review")
    fun addReview(
        @Body review :Review
    ): Call<LoginResponse>


    @POST("/api/v1/checkout")
    fun payment(
        @Header("Authorization") token: String,
        @Body paymentBody: PaymentBody
    ): Call<PaymentResponse>

    @POST("/api/v1/auth/login")
    fun login(
        @Body loginBody : LoginBody
    ): Call<LoginResponse>

    @POST("/api/v1/auth/oauth2")
    fun loginGoogle(
        @Body tokenBody: TokenBody
    ): Call<LoginResponse>

    @GET("/api/v1/variant/{id}")
    fun getVariants(@Path("id") id: String
    ):Call<VariantsResponse>

    @POST("/api/v1/carts")
    fun addCart(
        @Header("Authorization") token: String,
        @Body cart: Cart
        ):Call<CartResponse>

    @POST("/api/v1/raja-ongkir/cost")
    fun postOngkir(
        @Body pengirimanBody: PengirimanBody
    ):Call<OngkirResponse>

    @GET("/api/v1/carts")
    fun getCart(
        @Header("Authorization") token: String,
    ):Call<GetCartResponse>

    @GET("/api/v1/order")
    fun getOrders(
        @Header("Authorization") token: String,
    ):Call<OrdersResponse>

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

    @GET("/api/v1/product/search")
    suspend fun searchProduct(
        @Query("keyword") name: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): SearchResponse

    @GET("/api/v1/product")
    suspend fun getAllProduct() : TrendingResponse
}