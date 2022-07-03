package com.apps.finalproject.data.api

import com.apps.finalproject.model.LoginBody
import com.apps.finalproject.model.RegisterBody
<<<<<<< HEAD
import com.apps.finalproject.model.response.ArticleResponse
import com.apps.finalproject.model.response.LoginResponse
import com.apps.finalproject.model.response.RegisterResponse
import com.apps.finalproject.model.response.ReviewResponse
import retrofit2.Call
import retrofit2.http.*
=======
import com.apps.finalproject.model.response.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
>>>>>>> c3a28987262786506195d070a3215ab04da893f9

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

    @GET("/api/v1/product/q/trending")
    suspend fun getTrending() : TrendingResponse
}