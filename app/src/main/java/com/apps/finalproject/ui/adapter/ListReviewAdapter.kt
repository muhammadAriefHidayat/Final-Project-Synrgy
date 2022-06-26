package com.apps.finalproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.databinding.ItemListReviewsBinding
import com.apps.finalproject.model.Review
import com.apps.finalproject.model.response.ReviewResponse
import com.bumptech.glide.Glide

class ListReviewAdapter(private val listReview: List<ReviewResponse>) : RecyclerView.Adapter<ListReviewAdapter.ViewHolder>() {
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
            tvDescReview.text = review.data[position].content
//            Glide.with(binding.root.context)
//                .load(review.data.forEach {
//                    it.images
//                })
//                .circleCrop()
//                .into(ivUser)
//            tvName.text = review.userId
//            tvDate.text = review.date
//            tvDescReview.text = review.content
//            Glide.with(binding.root.context)
//                .load(review.imagesCount)
//                .circleCrop()
//                .into(ivReviewOne)
//            Glide.with(binding.root.context)
//                .load(review.imagesCount)
//                .circleCrop()
//                .into(ivReviewTwo)
        }
    }

    override fun getItemCount(): Int = listReview.size

}