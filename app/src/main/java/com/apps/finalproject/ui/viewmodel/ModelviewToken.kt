package com.apps.finalproject.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apps.finalproject.model.Token
import com.apps.finalproject.utils.AppPref
import com.apps.finalproject.utils.Utils.peringatan
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject


class ModelviewToken :ViewModel(){
    private val listToken = MutableLiveData<ArrayList<Token>>()

    fun setCurrentLogin(username: String,password: String,context: Context){
        val client = AsyncHttpClient()
        val url = "https://cosmetic-b.herokuapp.com/api/v1/auth/login"
        val dataToken  = ArrayList<Token>()
        val params = RequestParams()
        params.put("username", username);
        params.put("password", password);

        client.post(url, params, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>,
                responseString: String) {
                try {
                    AppPref.username = username
                    AppPref.password = password

                    Log.d("yangini", responseString.toString())
                    val responseObject = JSONObject(responseString)
                    val id_token = Token()
                    val data = responseObject.getString("data")
                    Log.d("yangini", "data $data")
                    id_token.token = responseObject.getString("token")
                    Log.d("yangini", " token ${id_token.token}")
                    dataToken.add(id_token)
                    listToken.postValue(dataToken)

                } catch (e: Exception) {
                    peringatan(context,e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?,
                throwable: Throwable?
            ) {
                peringatan(context,"Username Atau Password Salah")
            }
        })
    }

    internal fun getToken(): LiveData<ArrayList<Token>>{
        return listToken
    }
}