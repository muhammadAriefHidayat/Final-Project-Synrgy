package com.apps.finalproject.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.apps.finalproject.data.api.ApiServices
import com.apps.finalproject.databinding.FragmentRegisterBinding
import com.apps.finalproject.model.RegisterBody
import com.apps.finalproject.model.User
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.viewmodel.DetailViewModel
import kotlin.random.Random


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val detailViewModel: DetailViewModel by viewModels{ ViewModelFactory.getInstance(requireContext()) }

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
                    val user = User(name,"")
                    val dataUser = RegisterBody("kjlj",email,password,"ROLE_ADMIN",user)
//                        Register("string",email,password,role = "ROLE_ADMIN",user)


                }
            }
        }
    }

}