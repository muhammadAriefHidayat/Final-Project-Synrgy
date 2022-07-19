package com.apps.finalproject.ui.checkoutshiping

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.apps.finalproject.R
import com.apps.finalproject.remote.model.EkspedisiItem
import com.apps.finalproject.utils.Utils.getDrawableInt
import com.bumptech.glide.Glide
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class BankAdapter(val itemKurir: EkspedisiItem, val context: Context) : Item<GroupieViewHolder>() {


    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val imge = viewHolder.itemView.findViewById<ImageView>(R.id.img_kurir_item)
        val idDrawable = getDrawableInt(context, itemKurir.img)
        Glide.with(context)
            .load(idDrawable)
            .placeholder(R.drawable.home)
            .into(imge);
        viewHolder.itemView.findViewById<TextView>(R.id.tv_kurir_name).text = itemKurir.kurir
    }

    override fun getLayout(): Int {
        return R.layout.item_list_kurir
    }
}