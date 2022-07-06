package com.apps.finalproject.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apps.finalproject.databinding.ActivityProfileBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ProfileActivity : AppCompatActivity() {
    lateinit var  binding : ActivityProfileBinding
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}