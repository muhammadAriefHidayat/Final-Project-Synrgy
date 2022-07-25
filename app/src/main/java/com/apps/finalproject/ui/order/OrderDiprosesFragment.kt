package com.apps.finalproject.ui.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentOrderDiprosesBinding

class OrderDiprosesFragment : Fragment() {
    private val ordersViewModel:OrdersViewModel by activityViewModels()
    lateinit var bindin:FragmentOrderDiprosesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindin = FragmentOrderDiprosesBinding.inflate(layoutInflater,container,false)
        return bindin.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ordersViewModel.getResponse().observe(requireActivity()){
            if ((it == null) or (it.isNullOrEmpty())){
                bindin.layoutKosong.constraintKosong.visibility = View.VISIBLE
            }else{
                bindin.layoutKosong.constraintKosong.visibility = View.GONE
            }
        }
    }
}