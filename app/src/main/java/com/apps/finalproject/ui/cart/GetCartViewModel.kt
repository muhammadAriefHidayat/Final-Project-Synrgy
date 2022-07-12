package com.apps.finalproject.ui.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.model.Cart
import com.apps.finalproject.remote.response.CartResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetCartViewModel(private val repository: MainRepository) : ViewModel() {

    fun GetCart(cart: Cart) = viewModelScope.launch {
        repository.addCart(cart).collect {
            it.enqueue(object : Callback<CartResponse> {
                override fun onResponse(
                    call: Call<CartResponse>,
                    response: Response<CartResponse>
                ) {
                    Log.d("hasil", response.body()?.success.toString())
                }

                override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                    Log.d("hasil", "error")
                }

            })
        }
    }

}