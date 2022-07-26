package com.apps.finalproject.ui.payment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityFinishingPaymentBinding
import com.apps.finalproject.remote.response.DataPayment
import com.apps.finalproject.ui.order.OrderActivity
import com.apps.finalproject.ui.home.HomePageActivity
import com.apps.finalproject.utils.Utils.getDrawableInt
import com.apps.finalproject.utils.Utils.rupiah
import com.bumptech.glide.Glide

class FinishingPaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinishingPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishingPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentPayment = intent.getParcelableExtra<DataPayment>("payment")

        binding.iconVirtualimg

        val image = getDrawableInt(this,intentPayment?.bankName?.lowercase())
        Glide.with(this)
            .load(image)
            .placeholder(R.drawable.bca)
            .into(binding.iconVirtualimg)

        binding.apply {
            if (intentPayment != null) {
                tvNomorVirtual.text = intentPayment.vaNumber
                tvTotalPembayaran.text = rupiah(intentPayment.grossAmount.toDouble())
                val orderid = (intentPayment.orderId).split("-")
                tvIdPesanan.text = orderid[0].toString()
            }
        }

        binding.btnCekstatuspesanan.setOnClickListener {
            val dashBoardIntent = Intent(this, HomePageActivity::class.java)
            dashBoardIntent.putExtra("order",true)
            dashBoardIntent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(dashBoardIntent)
        }
        binding.btnBelanjaLagi.setOnClickListener {
            val dashBoardIntent = Intent(this, HomePageActivity::class.java)
            dashBoardIntent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(dashBoardIntent)
        }
    }
}