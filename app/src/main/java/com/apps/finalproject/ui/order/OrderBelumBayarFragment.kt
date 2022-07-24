package com.apps.finalproject.ui.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.apps.finalproject.R
import com.apps.finalproject.ui.ViewModelFactory

class OrderBelumBayarFragment : Fragment() {
    private val ordersViewModel:OrdersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_belum_bayar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ordersViewModel.getResponse().observe(requireActivity()){
            if (it.isNotEmpty() and (!it.isNullOrEmpty())){
                Log.d("orderbb", it[0].id.toString())
            }else{
                Log.d("order", "kosong")
            }
        }
    }
}