package com.apps.finalproject.ui.beautyfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentBeautyFeedBinding
import com.apps.finalproject.remote.model.Article
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.home.HomeViewModel

class BeautyFeedFragment : Fragment() {

    private lateinit var binding: FragmentBeautyFeedBinding
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBeautyFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getArticle()
        homeViewModel.article.observe(viewLifecycleOwner){
            showDataArticle(it)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
    }

    private fun showDataArticle(it: List<Article>) {
            val listBeautyFeedAdapter = ListBeautyFeedAdapter(it)
            binding.itemListBeautyFeedFragment.apply {
                setHasFixedSize(true)
                itemAnimator = DefaultItemAnimator()
                adapter = listBeautyFeedAdapter
            }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}