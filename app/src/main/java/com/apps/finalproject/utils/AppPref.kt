package com.apps.finalproject.utils

import android.content.Context
import android.content.SharedPreferences

object AppPref {

    private const val NAME = "SharePrefApp"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    //SharedPreferences variables
    private val TOKEN = Pair("token", "")
    private val USERNAME = Pair("username", "")
    private val EMAIL = Pair("email", "")
    private val USERID = Pair("userId", "")

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    //an inline function to put variable and save it
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var token: String
        get() = preferences.getString(TOKEN.first, TOKEN.second) ?: ""
        set(value) = preferences.edit(){
            it.putString(TOKEN.first, value)
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

    var userId: String
        get() = preferences.getString(USERID.first, USERID.second) ?: ""
        set(value) = preferences.edit(){
            it.putString(USERID.first, value)
        }

}