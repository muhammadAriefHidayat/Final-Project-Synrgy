package com.apps.finalproject.ui.cart

import android.content.Context
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.remote.response.CartItems
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CheckoutBarang(val cartItems: CartItems, val context: Context) : Item<GroupieViewHolder>() {
    private val adapteitems = GroupAdapter<GroupieViewHolder>()

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val brand = viewHolder.itemView.findViewById<TextView>(R.id.tv_brand_brand)
        val rv = viewHolder.itemView.findViewById<RecyclerView>(R.id.rv_itemvarian)
        brand.text = cartItems.brandName.toString()
        cartItems.items.forEach {
            adapteitems.add(CartItemVarAdapter(it,context))
        }
        rv.adapter = adapteitems
    }

    override fun getLayout(): Int {
        return R.layout.item_produk_brand
    }
}