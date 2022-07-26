package com.apps.finalproject.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.apps.finalproject.ui.home.HomePageActivity

class RoutingActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        // Keep the splash screen visible for this Activity
        splashScreen.setKeepOnScreenCondition { true }
        startActivity(Intent(this,HomePageActivity::class.java))
        finish()
    }
}