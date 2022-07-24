package com.apps.finalproject.ui.order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.response.DataOrders
import com.apps.finalproject.remote.response.OrdersResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersViewModel(private val repository: MainRepository) : ViewModel() {

    private val mResponse = MutableLiveData<List<DataOrders>?>()

    fun getOrders() = viewModelScope.launch {
        repository.getOrders().collect {
            it.enqueue(object : Callback<OrdersResponse> {
                override fun onResponse(
                    call: Call<OrdersResponse>,
                    response: Response<OrdersResponse>
                ) {
                    Log.d("ordervm", response.body()?.data.toString())
                    mResponse.postValue(response.body()?.data)
                }

                override fun onFailure(call: Call<OrdersResponse>, t: Throwable) {
                    Log.d("hasil", "error")
                }

            })
        }
    }

    internal fun getResponse(): MutableLiveData<List<DataOrders>?> {
        return mResponse
    }

}