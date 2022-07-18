package com.apps.finalproject.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityHomePageBinding

class HomePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navController = findNavController(R.id.fragmentContainerView)
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.homeFragment,
//                R.id.beautyFeedFragment,
//                R.id.orderFragment
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        binding.bottomNavigationHomeView.setupWithNavController(navController)

        binding.bottomNavigationHomeView.setOnNavigationItemSelectedListener {
            val bool: Boolean = if (it.itemId == R.id.myProfileFragment) {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            } else {
                false
            }
            return@setOnNavigationItemSelectedListener bool
        }
    }
}