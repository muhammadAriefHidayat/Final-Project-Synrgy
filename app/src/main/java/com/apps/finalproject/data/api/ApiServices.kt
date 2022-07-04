package com.apps.finalproject.data.api

import com.apps.finalproject.model.LoginBody
import com.apps.finalproject.model.RegisterBody
import com.apps.finalproject.model.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServices {
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