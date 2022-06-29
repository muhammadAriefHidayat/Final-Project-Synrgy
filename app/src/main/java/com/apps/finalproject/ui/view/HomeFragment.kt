package com.apps.finalproject.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentHomeBinding
import com.apps.finalproject.model.Article
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.ListArticleAdapter
import com.apps.finalproject.ui.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getArticle()
        homeViewModel.article.observe(viewLifecycleOwner){
            showDataArticle(it)
        }
    }

    private fun showDataArticle(listArticle: List<Article>) {
        val listArticleAdapter = ListArticleAdapter(listArticle)
        binding.itemListArticle.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = listArticleAdapter
        }
    }
}