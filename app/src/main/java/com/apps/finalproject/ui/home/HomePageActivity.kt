package com.apps.finalproject.ui.home


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityHomePageBinding


class HomePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intents = intent.getBooleanExtra("order",false)
        val intentp = intent.getBooleanExtra("profil",false)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.home_navigation)
        val bottomNavigationView = binding.bottomNavigationHomeView


        if (intents) graph.setStartDestination(R.id.OrdersFragment) else if (intentp) graph.setStartDestination(R.id.myProfileFragment)
        else graph.setStartDestination(R.id.HomeFragment)

        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)


    }
}