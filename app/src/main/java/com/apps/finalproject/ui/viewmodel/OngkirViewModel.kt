package com.apps.finalproject.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.body.PengirimanBody
import com.apps.finalproject.remote.model.Token
import com.apps.finalproject.remote.response.OngkirResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OngkirViewModel(private val repository: MainRepository) : ViewModel() {

    private val listToken = MutableLiveData<Token>()

    fun postOngkir(pengirimanBody: PengirimanBody) = viewModelScope.launch {
        repository.postOngkir(pengirimanBody).collect {
            it.enqueue(object : Callback<OngkirResponse> {
                override fun onResponse(
                    call: Call<OngkirResponse>,
                    response: Response<OngkirResponse>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<OngkirResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}