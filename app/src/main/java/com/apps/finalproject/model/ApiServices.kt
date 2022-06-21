package com.apps.finalproject.model

import com.apps.finalproject.model.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {
    @POST("/api/v1/auth/register")
    suspend fun register(
        @Body register: Register
    ): RegisterResponse
}