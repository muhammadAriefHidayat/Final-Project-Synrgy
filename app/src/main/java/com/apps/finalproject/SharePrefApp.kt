package com.apps.jasatitip.utils

import android.app.Application

//inisiate sharepreff
class SharePrefApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPref.init(this)
    }
}