package com.apps.finalproject.ui.register

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apps.finalproject.remote.model.Token
import com.apps.finalproject.utils.AppPref
import com.apps.finalproject.utils.Utils
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject

class RegisterViewModel (): ViewModel() {
    private val listToken = MutableLiveData<ArrayList<Token>>()

    fun setRegister(username: String, password: String, user: JSONObject, context: Context){
        val client = AsyncHttpClient(true, 80, 443)
        val url = "https://cosmetic-b.herokuapp.com/api/v1/auth/register"
        val dataToken  = ArrayList<Token>()
        val jsonParams = JSONObject()
        jsonParams.put("email", username);
        jsonParams.put("role", "ROLE_ADMIN");
        jsonParams.put("password", password);
        jsonParams.put("user",user)
        Log.d("yang0", jsonParams.toString())
        val entity = StringEntity(jsonParams.toString())

        client.post( context, url, entity, "application/json", object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>,
                                   responseString: String) {
                try {
                    AppPref.username = username
                    AppPref.pw = password
                    Log.d("yangini", responseString.toString())
                    val responseObject = JSONObject(responseString)
                    val id_token = Token()
                    val data = responseObject.getString("data")
                    Log.d("yang2", data)
                    id_token.token = JSONObject(data).getString("token")
                    Log.d("yang3", " token ${id_token.token}")
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
                Log.d("yanginis", responseString.toString())
            }
        })
    }

    internal fun getToken(): LiveData<ArrayList<Token>>{
        return listToken
    }
}
