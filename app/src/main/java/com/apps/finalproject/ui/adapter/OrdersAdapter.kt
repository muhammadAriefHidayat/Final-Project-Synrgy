package com.apps.finalproject.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.apps.finalproject.R
import com.apps.finalproject.remote.response.DataOrders
import com.apps.finalproject.utils.Utils.rupiah
import com.apps.finalproject.utils.Utils.setDateTime
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class OrdersAdapter(val itemOrders: DataOrders,val status : Int) : Item<GroupieViewHolder>() {



    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.context

        val tvIdPesanan = viewHolder.itemView.findViewById<TextView>(R.id.tv_obb_idorders)
        val tvtanggal = viewHolder.itemView.findViewById<TextView>(R.id.tv_obb_tanggal_pesanan)
        val foto = viewHolder.itemView.findViewById<ImageView>(R.id.iv_obb_varian)
        val tvtotal = viewHolder.itemView.findViewById<TextView>(R.id.tv_obb_totalprice)

        val tvnama = viewHolder.itemView.findViewById<TextView>(R.id.tv_obb_namevariant)
        val tvstatus = viewHolder.itemView.findViewById<TextView>(R.id.tv_obb_remainder_order)
        val orderid = (itemOrders.id).split("-")
        tvIdPesanan.text = orderid[0].toString()

        val tanggal = setDateTime(itemOrders.date)
        val rupiah = rupiah(itemOrders.total.toDouble())

        tvtanggal.text =  "Tanggal: $tanggal"
        tvtotal.text =  "Total: $rupiah"


        if (status == 1){
            tvstatus.text =  "Status : Terbayar"
            val btnbayar = viewHolder.itemView.findViewById<Button>(R.id.btn_bayar_adapter)
            btnbayar.visibility = View.GONE
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_order_belum_bayar
    }
}