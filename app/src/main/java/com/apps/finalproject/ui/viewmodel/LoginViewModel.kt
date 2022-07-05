package com.apps.finalproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.model.LoginBody
import com.apps.finalproject.model.Token
import com.apps.finalproject.model.response.LoginResponse
import com.apps.finalproject.utils.AppPref
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val repository: MainRepository) : ViewModel() {

    private val listToken = MutableLiveData<Token>()

    fun Login(loginBody: LoginBody) = viewModelScope.launch {
        repository.login(loginBody).collect {
            it.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.d("hasil", "sukses")
                    val dataToken = response.body()?.data
                    val token = Token()
                    token.token = dataToken?.token.toString()
                    val jwt = JWT(token.token)
                    val uid = jwt.getClaim("userId")
                    val email = jwt.getClaim("email")
                    val name = jwt.getClaim("name")
                    AppPref.userId = uid.asString().toString()
                    AppPref.pw = loginBody.password
                    AppPref.email = email.asString().toString()
                    AppPref.username = name.asString().toString()

                    listToken.postValue(token)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("hasil", "error")
                }
            })
        }
    }

    internal fun getToken(): LiveData<Token> {
        return listToken
    }
}