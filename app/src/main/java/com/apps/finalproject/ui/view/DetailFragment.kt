package com.apps.finalproject.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentDetailBinding
import com.apps.finalproject.model.Review
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.ListReviewAdapter
import com.apps.finalproject.ui.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels{ ViewModelFactory.getInstance(requireContext()) }

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

        detailViewModel.review.observe(viewLifecycleOwner){
            populateData(it)
        }
    }

    private fun populateData(listReview: List<Review>) {
        val listReviewAdapter = ListReviewAdapter(listReview)
        binding.rvReview.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = listReviewAdapter
        }
    }

}