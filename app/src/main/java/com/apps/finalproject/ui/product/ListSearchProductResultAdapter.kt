package com.apps.finalproject.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ItemListFavoriteBinding
import com.apps.finalproject.remote.model.DataProduct
import com.apps.finalproject.remote.model.ProductsItem
import com.apps.finalproject.ui.favorite.ListFavoriteAdapter
import com.bumptech.glide.Glide

class ListSearchProductResultAdapter(private val listProduct: List<ProductsItem>) : RecyclerView.Adapter<ListSearchProductResultAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemListFavoriteBinding


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = ItemListFavoriteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val listProductItem = listProduct[position]
        with(binding){
            Glide.with(binding.root.context)
                .load(listProductItem.images[0])
                .centerCrop()
                .placeholder(R.drawable.article_herbal)
                .into(imgItemAvatar)
            tvProductName.text = listProductItem.name
        }
    }

    override fun getItemCount(): Int = listProduct.size
}