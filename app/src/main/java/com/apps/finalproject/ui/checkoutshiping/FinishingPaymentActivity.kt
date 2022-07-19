package com.apps.finalproject.ui.checkoutshiping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityFinishingPaymentBinding

class FinishingPaymentActivity : AppCompatActivity() {
    private  lateinit var binding:ActivityFinishingPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishingPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}