package com.apps.finalproject.data.api


import androidx.viewbinding.BuildConfig
import com.apps.finalproject.utils.AppPref
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        private fun headerInterceptor(): Interceptor {
            return Interceptor { cain ->
                val request = cain.request()
                val headerInterceptorRequest = request.newBuilder()
//                    .header("Authorization", "Bearer ${AppPref.token}")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .method(request.method,request.body)
                    .build()

                cain.proceed(headerInterceptorRequest)
            }
        }

        private val client = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://cosmetic-b.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun getApiService(): ApiServices = retrofit.create(ApiServices::class.java)
    }
}