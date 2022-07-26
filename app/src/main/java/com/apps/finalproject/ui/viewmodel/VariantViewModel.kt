package com.apps.finalproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.model.Cart
import com.apps.finalproject.remote.model.Token
import com.apps.finalproject.remote.response.CartResponse
import com.apps.finalproject.remote.response.VariantsData
import com.apps.finalproject.remote.response.VariantsResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VariantViewModel(private val repository: MainRepository) : ViewModel() {


    private val mResponse = MutableLiveData<VariantsData>()

    fun getvarianst(id: String) = viewModelScope.launch {
        Log.d("vart",id.toString())
        repository.getVariants(id).collect {
            it.enqueue(object : Callback<VariantsResponse> {
                override fun onResponse(
                    call: Call<VariantsResponse>,
                    response: Response<VariantsResponse>
                ) {
                    mResponse.postValue(response.body()?.data)
                    Log.d("varian", response.body()?.data.toString())
                }

                override fun onFailure(call: Call<VariantsResponse>, t: Throwable) {
                    Log.d("hasilvar", "error")
                    Log.d("hasilvar", "error")
                }

            })
        }
    }

    internal fun getResponse(): LiveData<VariantsData> {
        return mResponse
    }

}