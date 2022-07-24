package com.apps.finalproject.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentProductBinding
import com.apps.finalproject.remote.model.Trending
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.ListTrendingAdapter
import com.apps.finalproject.ui.detail.DetailFragment
import com.apps.finalproject.ui.home.HomeViewModel
import com.apps.finalproject.utils.objectToString

class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getProductTrending()
        homeViewModel.trending.observe(viewLifecycleOwner){
            showProductTrending(it)
        }
    }

    private fun showProductTrending(listProductTrending: List<Trending>){
        val listTrendingAdapter = ListTrendingAdapter(listProductTrending)
        listTrendingAdapter.setOnItemClickListener(onItemClicked)
        binding.listProdukItem.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = listTrendingAdapter
        }
    }

    private val onItemClicked = object : ListTrendingAdapter.OnItemClickListener{
        override fun onItemClicked(data: Trending) {
            detailTrending(data)
        }
    }

    private fun detailTrending(data: Trending){
        val mBundle = bundleOf(DetailFragment.EXTRA_PRODUCT to objectToString(data))

        Navigation.findNavController(requireView()).navigate(R.id.action_productFragment_to_DetailFragment2, mBundle)

    }

}