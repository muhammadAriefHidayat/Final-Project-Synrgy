package com.apps.finalproject.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ItemListFavoriteBinding
import com.apps.finalproject.remote.model.Trending
import com.apps.finalproject.remote.model.Variant
import com.apps.finalproject.ui.adapter.ListTrendingAdapter
import com.bumptech.glide.Glide

class ListFavoriteAdapter (private val listTrending: List<Trending>) : RecyclerView.Adapter<ListFavoriteAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemListFavoriteBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemListFavoriteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productFavorite = listTrending[position]
        with(binding){
            Glide.with(binding.root.context)
                .load(productFavorite.images?.get(0))
                .centerCrop()
                .placeholder(R.drawable.article_herbal)
                .into(imgItemAvatar)
            tvProductName.text = productFavorite.variant?.get(0)?.name
            tvPrice.text = productFavorite.variant?.get(0)?.price.toString()
        }
    }
    override fun getItemCount(): Int = listTrending.size
}