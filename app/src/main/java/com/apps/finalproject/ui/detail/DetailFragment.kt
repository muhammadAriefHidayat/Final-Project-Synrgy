package com.apps.finalproject.ui.detail

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.databinding.FragmentDetailBinding
import com.apps.finalproject.remote.model.Review
import com.apps.finalproject.remote.model.Trending
import com.apps.finalproject.remote.model.toListVariant
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.ListReviewAdapter
import com.apps.finalproject.utils.Utils
import com.apps.finalproject.utils.stringToObject
import com.bumptech.glide.Glide


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


        val dataTrending = arguments?.getString(EXTRA_PRODUCT)
        val data = stringToObject(dataTrending, Trending::class.java)
        populateDataProduct(data)

        Log.d("getreview", "$dataTrending")
        detailViewModel.getReview()

        detailViewModel.review.observe(viewLifecycleOwner){
            populateData(it)

//                binding.imDetailProduk.setOnClickListener(
//                    Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_addReviewFragment)
//                )
        }
    }

    private fun populateDataProduct(dataTrending : Trending?){
        Log.d(TAG, "populateDataProduct: ${dataTrending?.variant?.get(0)?.name}")
        if (dataTrending != null){

        with(binding){
            Glide.with(this@DetailFragment)
                .load(dataTrending.images?.get(0))
                .into(imDetailProduk)
            tvDetailProduk.text = dataTrending.variant?.get(0)?.name ?: "-"
            tvHargaProduk.text = dataTrending.variant?.get(0)?.price.toString()
            ratingBarProduk.rating = dataTrending.average?.toFloat() ?: 0f
            tvQuantity.text = dataTrending.variant?.get(0)?.quantity.toString()
        }
        } else {
            Utils.peringatan(requireContext(),"Koneksi tidak stabil, Terjadi kesalahan")
            requireActivity().onBackPressed()
        }
    }

    private fun populateData(listReview: List<Review>) {
        val listReviewAdapter = ListReviewAdapter(listReview)
        binding.rvReview.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = listReviewAdapter
        }
    }

    companion object{
        const val EXTRA_PRODUCT = "product"
    }

}