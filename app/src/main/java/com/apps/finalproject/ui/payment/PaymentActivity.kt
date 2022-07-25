package com.apps.finalproject.ui.payment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityPaymentBinding
import com.apps.finalproject.remote.body.PaymentBody
import com.apps.finalproject.remote.response.DataPayment
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.KurirAdapter
import com.apps.finalproject.utils.Utils.rupiah
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class PaymentActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentBinding
    private val adaptebank = GroupAdapter<GroupieViewHolder>()
    var namebank = ""

    private val paymentviewmodel: PaymentViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val total = intent.getStringExtra("bayar")
        val kurir = intent.getStringExtra("transfer")


        binding.totalpembayran.text = rupiah(total!!.toDouble())
        paymentviewmodel.getbank()
        paymentviewmodel.getDataBank().observe(this) {
            it.forEach { bank ->
                adaptebank.add(KurirAdapter(bank, this))
            }
        }

        binding.btnBayarCheckout.setOnClickListener {
            binding.progressPaymenet.visibility = View.VISIBLE
            if (kurir != "") {
                setDataPembayaran(it, kurir!!)
            }
        }
        binding.linearPembayaran3.setOnClickListener {
            getMetodePembayaran(R.layout.fragment_payment_method)
        }
    }

    private fun getMetodePembayaran(layoutId: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutId)

        val lp = WindowManager.LayoutParams()
        if (dialog.window != null) {

            lp.copyFrom(dialog.window?.attributes)
            lp.gravity = Gravity.CENTER
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            val rvMetode = dialog.findViewById<RecyclerView>(R.id.rv_payment_bank)
            val positiveButton = dialog.findViewById<ImageView>(R.id.iv_close)

            adaptebank.setOnItemClickListener { item, view ->
                val itemekspedisi = item as KurirAdapter

                binding.textbank.text = itemekspedisi.itemKurir.kurir
                namebank = itemekspedisi.itemKurir.kurir
                dialog.cancel()
            }

            rvMetode.adapter = adaptebank
            positiveButton.setOnClickListener {
                dialog.cancel()
            }

            dialog.show()

            dialog.window?.attributes = lp
        }
    }


    private fun setDataPembayaran(view: View, kurir: String) {
        val data = PaymentBody(namebank.uppercase(), kurir, "REGULAR", "BANK_TRANSFER")
        paymentviewmodel.chekcout(data)
        paymentviewmodel.getDataPayment().observe(this) {
            if (it.toString() != "null") {
                binding.progressPaymenet.visibility = View.GONE
                val paymentData: DataPayment = it
                val intentData = Intent(this, FinishingPaymentActivity::class.java)
                intentData.putExtra("payment", paymentData)
                startActivity(intentData)
            }
        }
    }
}