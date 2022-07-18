package com.apps.finalproject.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ItemListFavoriteBinding
import com.apps.finalproject.remote.model.Variant
import com.bumptech.glide.Glide

class ListFavoriteAdapter (private val listVariant: List<Variant>) : RecyclerView.Adapter<ListFavoriteAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemListFavoriteBinding
    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemListFavoriteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productFavorite = listVariant[position]
        with(binding){
            Glide.with(binding.root.context)
                .load(productFavorite.imageIndex)
                .centerCrop()
                .placeholder(R.drawable.article_herbal)
                .into(imgItemAvatar)
            tvProductName.text = productFavorite.name
            tvPrice.text = productFavorite.price.toString()
            root.setOnClickListener{
                onItemClickListener.onItemClicked(productFavorite)
            }
        }
    }

    override fun getItemCount(): Int = listVariant.size

    interface OnItemClickListener{
        fun onItemClicked(data: Variant)
    }
}