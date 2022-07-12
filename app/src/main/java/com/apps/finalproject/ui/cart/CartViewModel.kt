package com.apps.finalproject.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.body.LoginBody
import com.apps.finalproject.remote.model.Cart
import com.apps.finalproject.remote.model.Token
import com.apps.finalproject.remote.response.CartResponse
import com.apps.finalproject.remote.response.LoginResponse
import com.apps.finalproject.utils.AppPref
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel(private val repository: MainRepository) : ViewModel() {

    private val listToken = MutableLiveData<Token>()

    fun AddCart(cart: Cart) = viewModelScope.launch {
        repository.addCart(cart).collect {
            it.enqueue(object : Callback<CartResponse> {
                override fun onResponse(
                    call: Call<CartResponse>,
                    response: Response<CartResponse>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    internal fun getToken(): LiveData<Token> {
        return listToken
    }
}