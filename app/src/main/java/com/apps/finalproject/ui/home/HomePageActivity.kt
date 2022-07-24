package com.apps.finalproject.ui.home


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityHomePageBinding


class HomePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = Navigation.findNavController(this, R.id.home_nav_host_fragment)
        val bottomNavigationView = binding.bottomNavigationHomeView
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

//        binding.bottomNavigationHomeView.setOnNavigationItemSelectedListener {
//            val bool: Boolean = if (it.itemId == R.id.myProfileFragment) {
//                if (AppPref.token == "") {
//                    Utils.peringatan(this, "harap login terlebih dahulu")
//                    startActivity(Intent(this, AuthActivity::class.java))
//                } else {
//                    startActivity(Intent(this, ProfileActivity::class.java))
//                }
//                true
//            } else {
//                false
//            } as Boolean
//            return@setOnNavigationItemSelectedListener bool
//        }
    }
}