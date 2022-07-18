package com.apps.finalproject.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityEditProfileBinding

private lateinit var binding: ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}