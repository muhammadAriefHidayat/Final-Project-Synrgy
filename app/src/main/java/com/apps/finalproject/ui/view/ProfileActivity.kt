package com.apps.finalproject.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.apps.finalproject.databinding.ActivityProfileBinding
import com.apps.finalproject.remote.model.ProfilItem
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.ProfilAdapter
import com.apps.finalproject.ui.favorite.FavoriteActivity
import com.apps.finalproject.ui.viewmodel.ProfileviewModel
import com.apps.finalproject.utils.AppPref
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ProfileActivity : AppCompatActivity() {

    private val profileViewModel: ProfileviewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    lateinit var binding: ActivityProfileBinding
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            tvName.text = AppPref.username
            tvEmailProfil.text = AppPref.email
            btnLogoutProfile.setOnClickListener {
                AppPref.token = ""
                AppPref.username = ""
                AppPref.email = ""
                startActivity(Intent(this@ProfileActivity, AuthActivity::class.java))
            }
        }

    }


}