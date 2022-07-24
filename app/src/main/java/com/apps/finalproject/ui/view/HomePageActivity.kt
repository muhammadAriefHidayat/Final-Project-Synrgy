package com.apps.finalproject.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityHomePageBinding
import com.apps.finalproject.ui.cart.CartActivity
import com.apps.finalproject.utils.AppPref
import com.apps.finalproject.utils.Utils

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
                if (AppPref.token == ""){
                    Utils.peringatan(this,"harap login terlebih dahulu")
                    startActivity(Intent(this, AuthActivity::class.java))
                }else{
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
                true
            } else if (it.itemId == R.id.beautyFeedFragmentTitle) {
                val navController = findNavController(R.id.nav_host_fragment)
                    navController.navigate(R.id.beautyFeedFragment)
            } else{
                false
            } as Boolean
            return@setOnNavigationItemSelectedListener bool
        }
    }
}