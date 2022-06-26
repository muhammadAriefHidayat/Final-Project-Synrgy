package com.apps.finalproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.databinding.ItemListReviewsBinding
import com.apps.finalproject.model.Review

class ListReviewAdapter(private val listReview: List<Review>) : RecyclerView.Adapter<ListReviewAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemListReviewsBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListReviewAdapter.ViewHolder {
        binding = ItemListReviewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = listReview[position]
        with(binding){

        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}