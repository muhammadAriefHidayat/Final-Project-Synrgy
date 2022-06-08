package com.apps.jasatitip.utils

import android.content.Context
import android.content.SharedPreferences

object AppPref {

    private const val NAME = "SharePrefApp"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    //SharedPreferences variables
    private val UID = Pair("uid", "")
    private val USERNAME = Pair("username", "")
    private val EMAIL = Pair("email", "")
    private val PASSWORD = Pair("password", "")
    private val JUSER = Pair("jenisUser", "")
    private val LATITUDE = Pair("latitude", "")
    private val LONGITUDE = Pair("longitude", "")

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    //an inline function to put variable and save it
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var uid: String
        get() = preferences.getString(UID.first, UID.second) ?: ""
        set(value) = preferences.edit(){
            it.putString(UID.first, value)
        }

    var latitude: String
        get() = preferences.getString(LATITUDE.first, LATITUDE.second) ?: ""
        set(value) = preferences.edit(){
            it.putString(LATITUDE.first, value)
        }

    var longitude: String
        get() = preferences.getString(LONGITUDE.first, LONGITUDE.second) ?: ""
        set(value) = preferences.edit(){
            it.putString(LONGITUDE.first, value)
        }

    var username: String
        get() = preferences.getString(USERNAME.first, USERNAME.second) ?: ""
        set(value) = preferences.edit(){
            it.putString(USERNAME.first, value)
        }

    var email: String
        get() = preferences.getString(EMAIL.first, EMAIL.second) ?: ""
        set(value) = preferences.edit(){
            it.putString(EMAIL.first, value)
        }

    var password: String
        get() = preferences.getString(PASSWORD.first, PASSWORD.second) ?: ""
        set(value) = preferences.edit(){
            it.putString(PASSWORD.first, value)
        }

    var jenisUser: String
        get() = preferences.getString(JUSER.first, JUSER.second) ?: ""
        set(value) = preferences.edit(){
            it.putString(JUSER.first, value)
        }
}