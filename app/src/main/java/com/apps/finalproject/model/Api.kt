package com.apps.finalproject.model


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {
    @Headers("Content-Type: application/json")
    @POST("/api/v1/auth/register")
    fun register(@Body userData: Register)

}