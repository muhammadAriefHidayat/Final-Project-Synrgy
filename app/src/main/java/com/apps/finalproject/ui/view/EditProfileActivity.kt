package com.apps.finalproject.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityEditProfileBinding
import com.apps.finalproject.ui.home.HomePageActivity
import com.apps.finalproject.utils.AppPref


class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            let {
                edtName.setText(AppPref.username)
                edtEmail.setText(AppPref.email)
                edtNomor.setText(AppPref.nomor)
            }

            btnSimpanProfile.setOnClickListener {
                AppPref.apply {
                    username = edtName.text.toString()
                    email =  edtName.text.toString()
                    nomor =  edtName.text.toString()
                }
                val intent = Intent(this@EditProfileActivity,HomePageActivity::class.java)
                intent.putExtra("profil",true)
                startActivity(intent)
                finish()
            }
        }
    }
}