package com.apps.finalproject.utils

import com.google.gson.Gson

fun <T> stringToObject(string: String?, outputClass: Class<T>?): T? {
    return Gson().fromJson(string, outputClass)
}

fun objectToString(T: Any?): String? {
    return Gson().toJson(T)
}
