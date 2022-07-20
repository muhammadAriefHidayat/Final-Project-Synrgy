package com.apps.finalproject.ui.checkoutshiping

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ContentInfoCompat
import com.apps.finalproject.databinding.ActivityFinishingPaymentBinding
import com.apps.finalproject.remote.response.DataPayment
import com.apps.finalproject.ui.order.OrderActivity
import com.apps.finalproject.ui.view.HomePageActivity

class FinishingPaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinishingPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishingPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentPayment = intent.getParcelableExtra<DataPayment>("payment")
        binding.apply {
            if (intentPayment != null){
                tvNomorVirtual.text = intentPayment.vaNumber
                tvTotalPembayaran.text = intentPayment.grossAmount.toString()
                val orderid = (intentPayment.orderId).split("-")
                tvIdPesanan.text = orderid[0].toString()
            }
        }

        binding.btnCekstatuspesanan.setOnClickListener {
            val dashBoardIntent = Intent(this,OrderActivity::class.java)
            dashBoardIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(dashBoardIntent)
        }
        binding.btnBelanjaLagi.setOnClickListener {
            val dashBoardIntent = Intent(this,HomePageActivity::class.java)
            dashBoardIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(dashBoardIntent)
        }
    }
}