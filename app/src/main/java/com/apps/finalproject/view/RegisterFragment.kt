package com.apps.finalproject.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apps.finalproject.databinding.FragmentRegisterBinding
import com.apps.finalproject.model.ApiService
import com.apps.finalproject.model.Register
import com.apps.finalproject.model.User
import kotlin.random.Random


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    var klik = Random(4)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDaftar.setOnClickListener {

            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            when {
                name.isEmpty() -> {
                    binding.edtName.error = "Masukkan Name"
                }
                email.isEmpty() -> {
                    binding.edtEmail.error = "Masukkan Email"
                }
                password.isEmpty() -> {
                    binding.edtPassword.error = "Masukkan Password"
                }
                else -> {
                    val apiService = ApiService()
                    val user = User(name,klik.toString())
                    val dataUser = Register("string",email,password,role = "ROLE_ADMIN",user)
                    apiService.Register(dataUser){
                        Log.d("hasil2",it.toString())
                    }
                }
            }
        }
    }

}