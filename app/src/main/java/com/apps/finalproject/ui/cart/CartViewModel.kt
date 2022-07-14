package com.apps.finalproject.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.model.Cart
import com.apps.finalproject.remote.model.Token
import com.apps.finalproject.remote.response.CartResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel(private val repository: MainRepository) : ViewModel() {

    private val mResponse = MutableLiveData<String>()

    fun AddCart(cart: Cart) = viewModelScope.launch {
        repository.addCart(cart).collect {
            it.enqueue(object : Callback<CartResponse> {
                override fun onResponse(
                    call: Call<CartResponse>,
                    response: Response<CartResponse>
                ) {
                    Log.d("cart","masuk")
                    Log.d("cart", response.body()?.status.toString())
                    mResponse.postValue(response.body()?.status.toString())
                }

                override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                    Log.d("hasil", "error")
                }

            })
        }
    }

    internal fun getResponse(): LiveData<String> {
        return mResponse
    }

}