package com.apps.finalproject.ui.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentOrderSelesaiBinding
import com.apps.finalproject.ui.adapter.OrdersAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class OrderSelesaiFragment : Fragment() {
    private val ordersViewModel:OrdersViewModel by activityViewModels()
    lateinit var binding:FragmentOrderSelesaiBinding
    private val adapters = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderSelesaiBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var cek = 0
        var total = 0

        ordersViewModel.getResponse().observe(requireActivity()){
            if ((it == null) or (it.isNullOrEmpty())){
                binding.layoutKosong.constraintKosong.visibility = View.VISIBLE
            }else{
                adapters.clear()
                adapters.notifyDataSetChanged()
                Log.d("order",it?.get(0).toString())
                val size = it?.size
                it?.forEach { mOrders ->
                    total+=1
                    if(mOrders.status == "DELIVERED"){
                        cek+=1
                        adapters.add(OrdersAdapter(mOrders,1))
                    }
                    if ((size == total) and (cek == 0)){
                        binding.layoutKosong.constraintKosong.visibility = View.VISIBLE
//                        binding.progresOrder.visibility = View.GONE
                    }
                }
                binding.rvOrdersDiproses.adapter = adapters
                binding.progresOrder.visibility = View.GONE
            }
        }
    }
}