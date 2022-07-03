package com.apps.finalproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.model.LoginBody
import com.apps.finalproject.model.response.LoginResponse
import com.apps.finalproject.utils.AppPref
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val repository: MainRepository) : ViewModel() {

    fun Login(loginBody: LoginBody) = viewModelScope.launch {
        repository.login(loginBody).collect {
            it.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.d("hasil", "sukses")
                    val dataToken = response.body()?.data

                    val jwt = JWT(dataToken?.token.toString())
                    val uid = jwt.getClaim("userId")
                    val email = jwt.getClaim("email")
                    val name = jwt.getClaim("name")
                    AppPref.userId = uid.asString().toString()
                    AppPref.pw = loginBody.password
                    AppPref.email = email.asString().toString()
                    AppPref.username = name.asString().toString()
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("hasil", "error")
                }
            })
        }
    }
}