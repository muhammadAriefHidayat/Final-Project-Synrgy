package com.apps.finalproject.ui.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.apps.finalproject.R
import com.apps.finalproject.remote.model.ProfilItem
import com.apps.finalproject.utils.Utils
import com.bumptech.glide.Glide
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class ProfilAdapter(val profilItem: ProfilItem, val context: Context) : Item<GroupieViewHolder>() {


    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val imge = viewHolder.itemView.findViewById<ImageView>(R.id.img_profil_item)
        val idDrawable = Utils.getDrawableInt(context,profilItem.img)
        Glide.with(context)
            .load(idDrawable)
            .placeholder(R.drawable.home)
            .into(imge);
        viewHolder.itemView.findViewById<TextView>(R.id.tv_email_profil).text =  profilItem.itemProfil
        viewHolder.itemView.setOnClickListener {
            context.startActivity(profilItem.intent)
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_list_profile
    }
}