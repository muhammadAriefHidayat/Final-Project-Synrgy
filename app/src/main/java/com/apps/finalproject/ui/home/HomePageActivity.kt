package com.apps.finalproject.ui.home


import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityHomePageBinding
import com.apps.finalproject.ui.adapter.PengirimanAdapter
import com.apps.finalproject.utils.Utils


class HomePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = Navigation.findNavController(this, R.id.home_nav_host_fragment)
        val bottomNavigationView = binding.bottomNavigationHomeView
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    override fun onBackPressed() {
        if (isTaskRoot) {
            yakinkeluar()
        } else {
            super.onBackPressed()
        }
    }

    private fun yakinkeluar() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Androidly Alert")
        builder.setMessage("Apakah Yakin ingin Keluar?")

        builder.setPositiveButton("Yakin") { dialog, which ->
            finish()
        }

        builder.setNegativeButton("Kembali") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
}