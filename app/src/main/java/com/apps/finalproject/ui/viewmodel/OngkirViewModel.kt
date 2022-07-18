package com.apps.finalproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.body.PengirimanBody
import com.apps.finalproject.remote.model.Token
import com.apps.finalproject.remote.response.Ekspedisi
import com.apps.finalproject.remote.response.OngkirResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OngkirViewModel(private val repository: MainRepository) : ViewModel() {

    private val listEkspedisi = MutableLiveData<Ekspedisi>()

    fun postOngkir(pengirimanBody: PengirimanBody) = viewModelScope.launch {
        repository.postOngkir(pengirimanBody).collect {
            it.enqueue(object : Callback<OngkirResponse> {
                override fun onResponse(
                    call: Call<OngkirResponse>,
                    response: Response<OngkirResponse>
                ) {
                    Log.d("ongkir", response.body()?.data?.get(0).toString())
                    listEkspedisi.postValue(response.body()?.data?.get(0))
                }

                override fun onFailure(call: Call<OngkirResponse>, t: Throwable) {
                    Log.d("ongkir", "errro")
                }
            })
        }
    }
    internal fun getExpedisi(): LiveData<Ekspedisi> {
        return listEkspedisi
    }
}