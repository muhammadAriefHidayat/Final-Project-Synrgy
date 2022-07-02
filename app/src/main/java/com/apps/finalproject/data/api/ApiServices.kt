package com.apps.finalproject.data.api

import com.apps.finalproject.model.LoginBody
import com.apps.finalproject.model.RegisterBody
import com.apps.finalproject.model.response.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {
    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ): LoginResponse

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