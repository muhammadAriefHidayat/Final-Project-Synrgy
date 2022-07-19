package com.apps.finalproject.ui.checkoutshiping

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
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ActivityCheckoutBinding
import com.apps.finalproject.remote.body.PengirimanBody
import com.apps.finalproject.remote.model.EkspedisiItem
import com.apps.finalproject.remote.response.CartItems
import com.apps.finalproject.remote.response.CartOverview
import com.apps.finalproject.remote.response.Ekspedisi
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.KurirAdapter
import com.apps.finalproject.ui.adapter.PengirimanAdapter
import com.apps.finalproject.ui.cart.CartBrandAdapter
import com.apps.finalproject.ui.cart.GetCartViewModel
import com.apps.finalproject.ui.viewmodel.OngkirViewModel
import com.apps.finalproject.utils.Utils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class CheckoutActivity : AppCompatActivity() {
    private val ongkirViewModel: OngkirViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }
    private val cartViewmodel: GetCartViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    lateinit var binding: ActivityCheckoutBinding
    private val adapterrv = GroupAdapter<GroupieViewHolder>()
    private val adapterkurir = GroupAdapter<GroupieViewHolder>()
    private val adapterPengiriman = GroupAdapter<GroupieViewHolder>()

    var kurir = ""
    var txOngkir = 0
    var txTotal = 0
    var txTotalAkhir = 0
    var metodePengiriman = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemKurir: MutableList<EkspedisiItem> = mutableListOf()
        itemKurir.add(EkspedisiItem("jne", "JNE"))
        itemKurir.add(EkspedisiItem("tiki", "TIKI"))
        itemKurir.add(EkspedisiItem("pos", "POS"))

        itemKurir.forEach {
            adapterkurir.add(KurirAdapter(it, this))
        }

        cartViewmodel.GetCart()
        cartViewmodel.getOverview().observe(this){
            setDataRingkasan(it)
        }
        cartViewmodel.getCartItems().observe(this){
            setCart(it)
        }

        binding.apply {
            btnMotodePengiriman.setOnClickListener {
                if (kurir == "") {
                    getKurir(R.layout.dialog_pilihan_kurir)
                } else {
                    val pengirimanBody = PengirimanBody("2", kurir, "1", 1)
                    ongkirViewModel.postOngkir(pengirimanBody)

                    ongkirViewModel.getExpedisi().observe(this@CheckoutActivity){
                        setData(it)
                    }

                }
            }
            tvPillihAlamat.setOnClickListener {

            }
            btnCeckoutPembayaran.setOnClickListener {
                if (txTotalAkhir==0){
                    Utils.peringatan(this@CheckoutActivity,"pilih metode pembayaran")
                }else{
                    val intent = Intent(this@CheckoutActivity,PaymentActivity::class.java)
                    intent.putExtra("bayar",txTotalAkhir.toString())
                    startActivity(intent)
                }

            }
            btnRedeemVoucer.setOnClickListener {

            }
            tvKurir.setOnClickListener {
                getKurir(R.layout.dialog_pilihan_kurir)
            }
        }
    }

    private fun setCart(it: List<CartItems>?) {
        if(it?.get(0)?.id != null){
            it.forEach { cartItems ->
                adapterrv.add(CheckoutBrandAdapter(cartItems,this))
            }
            binding.reycleviewRingkasanBelanja.apply {
                visibility = View.VISIBLE
                setHasFixedSize(true)
                itemAnimator = DefaultItemAnimator()
                adapter = adapterrv
            }
        }
    }

    private fun setDataRingkasan(it: CartOverview?) {
        if (it?.total != null){
            binding.apply {
                tvSubtotal.text = it.total.toString()
                txTotal = it.total
                tvTotalPembayaran.text = "${it.total + txOngkir}"
            }
        }
    }

    private fun setData(ekspedisi: Ekspedisi?) {
        adapterPengiriman.clear()
        adapterPengiriman.notifyDataSetChanged()
        ekspedisi?.costs?.forEach {
            adapterPengiriman.add(PengirimanAdapter(it,ekspedisi?.code.toString(),this))
        }

        getMetodePengiriman(R.layout.dialog_metode_pengiriman,ekspedisi?.code.toString())
    }

    private fun getMetodePengiriman(layoutId: Int,ekspediri:String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutId)

        val lp = WindowManager.LayoutParams()
        if (dialog.window != null) {

            lp.copyFrom(dialog.window?.attributes)
            lp.gravity = Gravity.CENTER
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            val rvMetode = dialog.findViewById<RecyclerView>(R.id.rv_pengiriman)
            val positiveButton = dialog.findViewById<ImageView>(R.id.btn_x_pengiriman)

            adapterPengiriman.setOnItemClickListener { item, view ->
                val itemekspedisi = item as PengirimanAdapter
                metodePengiriman =  "${ekspediri.uppercase()} ${itemekspedisi.itemService.service}"
                binding.apply {
                    tvMetodePengiriman.text = metodePengiriman
                    tvOngkoskirim.text = "${itemekspedisi.itemService.cost[0].value.toString()}"
                    txOngkir = itemekspedisi.itemService.cost[0].value
                    txTotalAkhir = txTotal + txOngkir
                    tvTotalPembayaran.text = txTotalAkhir.toString()
                    dialog.cancel()
                }
            }

            rvMetode.adapter = adapterPengiriman
            positiveButton.setOnClickListener {
                dialog.cancel()
            }

            dialog.show()

            dialog.window?.attributes = lp
        }
    }

    private fun getKurir(layoutId: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutId)

        val lp = WindowManager.LayoutParams()
        if (dialog.window != null) {

            lp.copyFrom(dialog.window?.attributes)
            lp.gravity = Gravity.CENTER
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            val rv = dialog.findViewById<RecyclerView>(R.id.rv_kurir)
            val positiveButton = dialog.findViewById<ImageView>(R.id.btn_x)

            adapterkurir.setOnItemClickListener { item, view ->
                val itemkurir = item as KurirAdapter
                kurir = itemkurir.itemKurir.kurir
                dialog.cancel()
                binding.tvKurir.text = "Ekspedisi $kurir"
                dialog.cancel()
            }

            rv.adapter = adapterkurir

            positiveButton.setOnClickListener {
//                this.startActivity(Intent(this, CartActivity::class.java))
                dialog.cancel()
            }

            dialog.show()

            dialog.window?.attributes = lp
        }
    }
}