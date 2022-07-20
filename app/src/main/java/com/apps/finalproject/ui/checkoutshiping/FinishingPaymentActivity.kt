package com.apps.finalproject.ui.checkoutshiping

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apps.finalproject.databinding.ActivityFinishingPaymentBinding
import com.apps.finalproject.ui.view.HomePageActivity

class FinishingPaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinishingPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishingPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCekstatuspesanan.setOnClickListener {
            startActivity(Intent(this,HomePageActivity::class.java))
        }
        binding.btnBelanjaLagi.setOnClickListener {
            startActivity(Intent(this,HomePageActivity::class.java))
        }
    }
}