package com.apps.finalproject.ui.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.apps.finalproject.R
import com.apps.finalproject.remote.model.EkspedisiItem
import com.apps.finalproject.remote.response.Ekspedisi
import com.apps.finalproject.remote.response.Service
import com.apps.finalproject.utils.Utils.getDrawableInt
import com.bumptech.glide.Glide
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class PengirimanAdapter(val itemService: Service,val ekspedisi: String, val context: Context) : Item<GroupieViewHolder>() {


    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.findViewById<TextView>(R.id.tv_code_kurir).text = "${ekspedisi.uppercase()} ${itemService.service}"
        viewHolder.itemView.findViewById<TextView>(R.id.tv_estimasi).text = "${itemService.cost[0].etd} hari"
        viewHolder.itemView.findViewById<TextView>(R.id.harga_service).text = "(${itemService.cost[0].value.toString()})"
    }

    override fun getLayout(): Int {
        return R.layout.item_kurir
    }
}