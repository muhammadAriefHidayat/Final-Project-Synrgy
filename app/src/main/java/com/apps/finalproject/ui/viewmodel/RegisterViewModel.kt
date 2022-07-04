package com.apps.finalproject.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.finalproject.data.repository.MainRepository
import com.apps.finalproject.model.RegisterBody
import com.apps.finalproject.model.Review
import com.apps.finalproject.model.Token
import com.apps.finalproject.model.User
import com.apps.finalproject.utils.AppPref
import com.apps.finalproject.utils.Utils
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.HttpHeaders
import kotlinx.coroutines.launch
import org.json.JSONObject

class RegisterViewModel (): ViewModel() {
    private val listToken = MutableLiveData<ArrayList<Token>>()

    fun setRegister(username: String, password: String, user: User, context: Context){
        val client = AsyncHttpClient()
        val url = "https://cosmetic-b.herokuapp.com/api/v1/auth/register"
        val dataToken  = ArrayList<Token>()
        val params = RequestParams()
        params.put("email", username);
        params.put("password", password);
        params.put("user", user);
        client.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        client.post(url, params, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>,
                                   responseString: String) {
                try {
                    AppPref.username = username
                    AppPref.pw = password
                    Log.d("yangini", responseString.toString())
                    val responseObject = JSONObject(responseString)
                    val id_token = Token()
                    id_token.token = responseObject.getString("token")
                    Log.d("yangini", id_token.token.toString())
                    dataToken.add(id_token)
                    listToken.postValue(dataToken)
                } catch (e: Exception) {
                    Utils.peringatan(context, e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?,
                throwable: Throwable?
            ) {
                Utils.peringatan(context, "Username Atau Password Salah")
            }
        })
    }

    internal fun getToken(): LiveData<ArrayList<Token>>{
        return listToken
    }
}
