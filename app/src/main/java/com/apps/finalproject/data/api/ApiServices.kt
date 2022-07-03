package com.apps.finalproject.data.api

import com.apps.finalproject.model.LoginBody
import com.apps.finalproject.model.RegisterBody
import com.apps.finalproject.model.response.ArticleResponse
import com.apps.finalproject.model.response.LoginResponse
import com.apps.finalproject.model.response.RegisterResponse
import com.apps.finalproject.model.response.ReviewResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @Headers("Content-Type: application/json")
    @POST("/api/v1/auth/login")
    fun login(
        @Body loginBody : LoginBody
    ): Call<LoginResponse>

    @POST("/api/v1/auth/register")
    fun register(
        @Body register: RegisterBody
    ): RegisterResponse

    @GET("/api/v1/review")
    suspend fun getReview(
    ): ReviewResponse

    @GET("/api/v1/article")
    suspend fun getArticle() : ArticleResponse
}