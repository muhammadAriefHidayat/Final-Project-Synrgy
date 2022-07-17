package com.apps.finalproject.ui.checkoutshiping

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.core.content.ContextCompat
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityCheckoutBinding
import com.apps.finalproject.ui.cart.CartActivity

class CheckoutActivity : AppCompatActivity() {
    lateinit var binding :ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
//            btnMotodePembayaran.setOnClickListener {
//
//            }
            btnMotodePengiriman.setOnClickListener {

            }
            tvPillihAlamat.setOnClickListener {

            }
            btnRedeemVoucer.setOnClickListener {

            }

        }
    }

    private fun get(layoutId: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutId)

        val lp = WindowManager.LayoutParams()
        if (dialog.window != null) {

            lp.copyFrom(dialog.window?.attributes)
            lp.gravity = Gravity.BOTTOM
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            val positiveButton = dialog.findViewById<Button>(R.id.btn_keranjang_saya)
//            val text = dialog.findViewById<TextView>(R.id.tx_info)
//            text.text = "Gagal memasukkan ke keranjang"

            positiveButton.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary))
            positiveButton.setOnClickListener {
                this.startActivity(Intent(this, CartActivity::class.java))
                dialog.cancel()
            }

            dialog.show()

            dialog.window?.attributes = lp
        }
    }
}