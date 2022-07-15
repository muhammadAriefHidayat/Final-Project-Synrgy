package com.apps.finalproject.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.response.CartItems
import com.apps.finalproject.remote.response.CartOverview
import com.apps.finalproject.remote.response.GetCartResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetCartViewModel(private val repository: MainRepository) : ViewModel() {

    private val overview = MutableLiveData<CartOverview>()
    private val cartItems = MutableLiveData<List<CartItems>>()


    fun GetCart() = viewModelScope.launch {
        repository.getCart().collect {
            Log.d("test",    it.toString())
            it.enqueue(object : Callback<GetCartResponse> {
                override fun onResponse(call: Call<GetCartResponse>,response: Response<GetCartResponse>) {
                    Log.d("test",response.body()?.data.toString())
                    overview.postValue(response.body()?.data?.overview)
                    cartItems.postValue(response.body()?.data?.cartItems)
                }

                override fun onFailure(call: Call<GetCartResponse>, t: Throwable) {
                    Log.d("test","error")
                    Log.d("test",t.message.toString())
                }
            })
        }
    }

    internal fun getOverview(): LiveData<CartOverview> {
        return overview
    }
    internal fun getCartItems(): LiveData<List<CartItems>> {
        return cartItems
    }

}