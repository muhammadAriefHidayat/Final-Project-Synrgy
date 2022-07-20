package com.apps.finalproject.ui.checkoutshiping

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityPaymentBinding
import com.apps.finalproject.remote.body.PaymentBody
import com.apps.finalproject.remote.model.EkspedisiItem
import com.apps.finalproject.remote.response.DataPayment
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.KurirAdapter
import com.apps.finalproject.ui.adapter.PengirimanAdapter
import com.apps.finalproject.ui.login.LoginViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class PaymentActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentBinding
    private val adaptebank = GroupAdapter<GroupieViewHolder>()

    private val paymentviewmodel : PaymentViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val total  = intent.getStringExtra("bayar")
        val bank  = intent.getStringExtra("transfer")

        binding.totalpembayran.text = total.toString()


        val itembank: MutableList<EkspedisiItem> = mutableListOf()
        itembank.add(EkspedisiItem("bank_bca","BCA"))
        itembank.add(EkspedisiItem("bank_bni","BNI"))
        itembank.add(EkspedisiItem("bank_bri","BRI"))
        itembank.add(EkspedisiItem("bank_mandiri","PERMATA"))

        itembank.forEach {
            adaptebank.add(KurirAdapter(it, this))
        }

        binding.btnBayarCheckout.setOnClickListener {
            val data = PaymentBody("BCA","JNE","REGULAR","BANK_TRANSFER")
            paymentviewmodel.chekcout(data)
            paymentviewmodel.getDataPayment().observe(this){
                if (it.toString() != "null"){
                    val paymentData:DataPayment = it
                    val intentData = Intent(this,FinishingPaymentActivity::class.java)
                    intentData.putExtra("payment",paymentData)
                    startActivity(intentData)
                }
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


}