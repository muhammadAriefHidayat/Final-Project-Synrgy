package com.apps.finalproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.model.LoginBody
import com.apps.finalproject.model.response.LoginResponse

import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel(private val repository: MainRepository):ViewModel() {

    fun Login(loginBody: LoginBody) = viewModelScope.launch {
        repository.login(loginBody).collect{
            it.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    Log.d("hasil", "sukses")
                    val dataTOken = response.body()?.data
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("hasil", "error")
                }
            })
        }
    }
}