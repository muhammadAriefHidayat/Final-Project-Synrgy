package com.apps.finalproject.ui.checkoutshiping

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.body.LoginBody
import com.apps.finalproject.remote.body.PaymentBody
import com.apps.finalproject.remote.model.Token
import com.apps.finalproject.remote.response.LoginResponse
import com.apps.finalproject.remote.response.PaymentResponse
import com.apps.finalproject.utils.AppPref
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentViewModel(private val repository: MainRepository) : ViewModel() {

//    private val listToken = MutableLiveData<Token>()

    fun chekcout(paymentBody: PaymentBody) = viewModelScope.launch {
        repository.payment(paymentBody).collect {
            it.enqueue(object : Callback<PaymentResponse> {
                override fun onResponse(
                    call: Call<PaymentResponse>,
                    response: Response<PaymentResponse>
                ) {
                    Log.d("payment",response.errorBody().toString())
                    Log.d("payment",response.errorBody().toString())
                    Log.d("payment",response.body()?.status.toString())
                }
                override fun onFailure(call: Call<PaymentResponse>, t: Throwable) {
                    Log.d("payment","payment error")
                }

            })
        }
    }

//    internal fun getToken(): LiveData<Token> {
//        return listToken
//    }
}