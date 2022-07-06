package com.apps.finalproject.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.databinding.FragmentDetailBinding
import com.apps.finalproject.remote.model.Review
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.ListReviewAdapter

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            Log.d("getreview", "onViewCreated: detail")
            detailViewModel.getReview()

            detailViewModel.review.observe(viewLifecycleOwner){
            populateData(it)

//                binding.imDetailProduk.setOnClickListener(
//                    Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_addReviewFragment)
//                )
        }
    }

//    private fun populateDataProduct(productTrend: Trending){
//        with(binding){
//            Glide.with(this@DetailFragment)
//                .load(productTrend.images[0])
//                .into(imDetailProduk)
//            tvDetailProduk.text = productTrend.variant.toListVariant()[0].name
//            tvHargaProduk.text = productTrend.variant.toListVariant()[0].price.toString()
//            ratingBarProduk.rating = productTrend.average.toFloat()
//            tvMl.text = productTrend.variant.toListVariant()[0].quantity.toString()
//        }
//    }

    private fun populateData(listReview: List<Review>) {
        val listReviewAdapter = ListReviewAdapter(listReview)
        binding.rvReview.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = listReviewAdapter
        }
    }

}