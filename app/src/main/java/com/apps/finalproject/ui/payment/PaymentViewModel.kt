package com.apps.finalproject.ui.payment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.remote.body.PaymentBody
import com.apps.finalproject.remote.model.EkspedisiItem
import com.apps.finalproject.remote.response.DataPayment
import com.apps.finalproject.remote.response.PaymentResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentViewModel(private val repository: MainRepository) : ViewModel() {

    private val dataPayment = MutableLiveData<DataPayment>()
    private val dataBank = MutableLiveData<List<EkspedisiItem>>()

    fun getbank() = viewModelScope.launch {
        val itembank: MutableList<EkspedisiItem> = mutableListOf()
        itembank.add(EkspedisiItem("bca","BCA"))
        itembank.add(EkspedisiItem("bni","BNI"))
        itembank.add(EkspedisiItem("bri","BRI"))
        itembank.add(EkspedisiItem("bca","PERMATA"))

        dataBank.postValue(itembank)
    }

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
                    dataPayment.postValue(response.body()?.data)
                }
                override fun onFailure(call: Call<PaymentResponse>, t: Throwable) {
                    Log.d("payment","payment error")
                }

            })
        }
    }

    internal fun getDataBank(): LiveData<List<EkspedisiItem>> {
        return dataBank
    }

    internal fun getDataPayment(): LiveData<DataPayment> {
        return dataPayment
    }
}