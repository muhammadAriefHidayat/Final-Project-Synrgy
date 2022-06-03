package com.apps.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.finalproject.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            val email = binding.editText.text.toString()
            val password = binding.edtEmail.text.toString()
            when {
                email.isEmpty() -> {
                    binding.editText.error = "Masukkan Email"
                }
                password.isEmpty() -> {
                    binding.edtEmail.error = "Masukkan Password"
                }
            }
        }
    }
}