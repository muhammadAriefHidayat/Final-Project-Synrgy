package com.apps.finalproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.ItemListReviewsBinding
import com.apps.finalproject.remote.model.Review
import com.bumptech.glide.Glide

class ListReviewAdapter(private val listReview: List<Review>) : RecyclerView.Adapter<ListReviewAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemListReviewsBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = ItemListReviewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = listReview[position]
        with(binding){
            Glide.with(binding.root.context)
                .load(review.user.avatar)
                .circleCrop()
                .into(ivUser)
            tvName.text = review.user.name
            tvDate.text = review.date
            tvDescReview.text = review.content
            Glide.with(binding.root.context)
                .load(review.images[0])
                .placeholder(R.drawable.img_produk)
                .into(ivReviewOne)
            Glide.with(binding.root.context)
                .load(review.images[1])
                .placeholder(R.drawable.img_produk)
                .into(ivReviewTwo)
        }
    }

    override fun getItemCount(): Int = listReview.size

}