package com.apps.finalproject.model

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {
    @POST("/api/v1/auth/register")
    suspend fun register(
        @Body register: Register
    )
}