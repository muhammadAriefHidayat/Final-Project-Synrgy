package com.apps.finalproject.data.api

import com.apps.finalproject.model.LoginBody
import com.apps.finalproject.model.RegisterBody
import com.apps.finalproject.model.response.LoginResponse
import com.apps.finalproject.model.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {
    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ): LoginResponse

    @POST("/api/v1/auth/register")
    suspend fun register(
        @Body register: RegisterBody
    ): RegisterResponse
}