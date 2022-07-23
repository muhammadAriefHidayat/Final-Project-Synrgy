package com.apps.finalproject.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.finalproject.databinding.FragmentOrdersBinding
import com.apps.finalproject.ui.checkoutshiping.SectionAdapter

class OrdersFragment : Fragment() {
    private var sectionpageAdapter: SectionAdapter? = null
    lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sectionpageAdapter = SectionAdapter(childFragmentManager)
        binding.dashViewPagger.adapter = sectionpageAdapter
        binding.tablayoutid.setupWithViewPager(binding.dashViewPagger)
    }
}