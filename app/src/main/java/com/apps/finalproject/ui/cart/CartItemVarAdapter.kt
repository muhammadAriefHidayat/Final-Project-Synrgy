package com.apps.finalproject.ui.cart

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.remote.response.CartItems
import com.apps.finalproject.remote.response.ItemVarian
import com.bumptech.glide.Glide
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CartItemVarAdapter(val itemVarian: ItemVarian, val context: Context) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val varians = viewHolder.itemView.findViewById<TextView>(R.id.tv_itemProdukAdapterCheck)
        val foto = viewHolder.itemView.findViewById<ImageView>(R.id.iv_cart_adapter_varian)
        val harga = viewHolder.itemView.findViewById<TextView>(R.id.tv_itemHargqAdapterCheck)

        varians.text = itemVarian.name
        harga.text = itemVarian.price.toString()
        Glide.with(context)
            .load(itemVarian.image)
            .placeholder(R.drawable.img_produk)
            .into(foto)

    }

    override fun getLayout(): Int {
        return R.layout.item_produk
    }
}