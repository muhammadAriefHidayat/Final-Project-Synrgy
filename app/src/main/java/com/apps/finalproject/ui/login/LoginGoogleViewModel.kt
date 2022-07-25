package com.apps.finalproject.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.body.TokenBody
import com.apps.finalproject.remote.model.Token
import com.apps.finalproject.remote.response.LoginResponse
import com.apps.finalproject.utils.AppPref
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginGoogleViewModel(private val repository: MainRepository) : ViewModel() {

    private val listToken = MutableLiveData<Token>()

    fun LoginGoogle(token: TokenBody) = viewModelScope.launch {
        repository.loginGoogle(token).collect {
            it.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val status =  response.body()?.success
                    val token = Token()
                    if (status == true){
                        val dataToken = response.body()?.data
                        try {
                            token.token = dataToken?.token.toString()
                            Log.d("token", token.token!!)
                            val jwt = JWT(token.token!!)
                            val uid = jwt.getClaim("userId")
                            val email = jwt.getClaim("email")
                            val name = jwt.getClaim("name")

                            AppPref.userId = uid.asString().toString()
                            AppPref.token = token.token
                            AppPref.email = email.asString().toString()
                            AppPref.username = name.asString().toString()

                            listToken.postValue(token)
                        }catch (e: Throwable){
                            Log.d("errr",e.message.toString())
                        }
                    }else{
                        token.token = ""
                        listToken.postValue(token)
                    }
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