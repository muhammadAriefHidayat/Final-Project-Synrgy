package com.apps.finalproject.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentLoginBinding
import com.apps.finalproject.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.linearPembayaran3.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_paymentFragment_to_paymentMethodFragment)
        }
    }
}