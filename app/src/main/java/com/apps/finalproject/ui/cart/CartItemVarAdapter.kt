package com.apps.finalproject.ui.cart

import android.content.Context
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.remote.response.CartItems
import com.apps.finalproject.remote.response.ItemVarian
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CartItemVarAdapter(val itemVarian: ItemVarian, val context: Context) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val varians = viewHolder.itemView.findViewById<TextView>(R.id.tv_items_varians_varians)
        varians.text = itemVarian.name
    }

    override fun getLayout(): Int {
        return R.layout.item_produk_cart
    }
}