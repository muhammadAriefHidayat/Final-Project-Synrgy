package com.apps.finalproject.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var  binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}