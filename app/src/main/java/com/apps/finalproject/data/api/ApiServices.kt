package com.apps.finalproject.data.api

import com.apps.finalproject.remote.body.LoginBody
import com.apps.finalproject.remote.body.RegisterBody
import com.apps.finalproject.remote.model.Review
import com.apps.finalproject.remote.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServices {
    @Headers("Content-Type: application/json")
    @POST("/api/v1/review")
    fun addReview(
        @Body review :Review
    ): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/auth/login")
    fun login(
        @Body loginBody : LoginBody
    ): Call<LoginResponse>

    @Headers("Content-Type: application/json")
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
}