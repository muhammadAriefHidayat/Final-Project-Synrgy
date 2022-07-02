package com.apps.finalproject.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apps.finalproject.databinding.FragmentLoginBinding
import com.apps.finalproject.ui.viewmodel.ModelviewToken
import com.apps.finalproject.utils.AppPref
import com.apps.finalproject.utils.Utils.loading

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var dataToken: ModelviewToken

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
                else -> {
                    logoin(email,password)
                }
            }
        }
    }

    fun logoin(username: String, password: String) {
        dataToken = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ModelviewToken::class.java
        )
        dataToken.setCurrentLogin(username, password, requireContext())
        dataToken.getToken().observe(requireActivity(), Observer { token ->
            if (token != null) {
                val token = token[0].token
                AppPref.token = token
            }
        })
    }
}