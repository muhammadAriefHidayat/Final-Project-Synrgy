package com.apps.finalproject.model

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ApiService {

    fun Register(userData: Register, onResult: (Register?) -> Unit){
        val retrofit = ServiceBuilder.buildService(Api::class.java)
        retrofit.register(userData).enqueue(object :Callback<com.apps.finalproject.model.Response>{
            override fun onResponse(
                call: Call<com.apps.finalproject.model.Response>,
                response: Response<com.apps.finalproject.model.Response>
            ) {
                Log.d("hasil", response.body().toString())
                Log.d("hasil", response.body()?.data.toString())
            }

            override fun onFailure(call: Call<com.apps.finalproject.model.Response>, t: Throwable) {
                Log.d("hasil", "Error")
            }
        })
    }
}