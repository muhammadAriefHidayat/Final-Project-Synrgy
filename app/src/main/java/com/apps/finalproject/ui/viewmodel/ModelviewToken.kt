package com.apps.finalproject.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apps.finalproject.data.api.ApiServices
import com.apps.finalproject.remote.model.Token
import com.apps.finalproject.utils.Utils.peringatan
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ModelviewToken : ViewModel() {
    private val listToken = MutableLiveData<Token>()

    fun setCurrentLogin(username: String, password: String, context: Context) {
        val client = AsyncHttpClient(true, 80, 443)
        val url = "https://cosmetic-b.herokuapp.com/api/v1/auth/login"

        val jsonParams = JSONObject()
        jsonParams.put("email", username);
        jsonParams.put("password", password);
        Log.d("yang0", jsonParams.toString())

        val entity = StringEntity(jsonParams.toString())

        client.post( context, url, entity, "application/json", object : TextHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int, headers: Array<out Header>,
                responseString: String
            ) {
                try {
                    Log.d("yang1", responseString.toString())
                    val responseObject = JSONObject(responseString)
                    val id_token = Token()
                    val data = responseObject.getString("data")
                    Log.d("yang2", data)
                    id_token.token = JSONObject(data).getString("token")
                    Log.d("yang3", " token ${id_token.token}")
                    listToken.postValue(id_token)

                } catch (e: Exception) {
                    peringatan(context, e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?,
                throwable: Throwable?
            ) {
                Log.d("yangsalah", responseString.toString())
                peringatan(context, "Username Atau Password Salah")
            }
        })
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(ApiServices::class.java)

    internal fun getToken(): LiveData<Token> {
        return listToken
    }
}