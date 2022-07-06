package com.apps.finalproject.ui.adapter

import android.content.Context
import android.widget.TextView
import com.apps.finalproject.R
import com.apps.finalproject.model.ProfilItem
import com.bumptech.glide.Glide
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class ProfilAdapter(val profilItem: ProfilItem, val context: Context) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.img_profil_item).text =
            profilItem.itemProfil
        Glide.with(context)
            .load(profilItem.img)
            .placeholder(R.drawable.home)
            .into(viewHolder.itemView.findViewById(R.id.img_profil_item));
    }

    override fun getLayout(): Int {
        return R.layout.item_list_profile
    }
}