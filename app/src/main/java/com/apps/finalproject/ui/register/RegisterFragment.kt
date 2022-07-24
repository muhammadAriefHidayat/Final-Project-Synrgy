package com.apps.finalproject.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentRegisterBinding
import com.apps.finalproject.remote.model.User
import com.apps.finalproject.ui.home.HomePageActivity
import com.apps.finalproject.utils.AppPref
import org.json.JSONObject
import kotlin.random.Random


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var dataToken: RegisterViewModel

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

        binding.tvMasuk.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_registerFragment_to_loginFragment)
        )
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
                    val user = User(name,"skinteyp")
                    val jsonuser = JSONObject()
                    jsonuser.put("name", name);
                    jsonuser.put("skinType", "skinteyp");
                        register(email,password,jsonuser)
                }
            }
        }
    }
    fun register(username: String, password: String,user: JSONObject) {
        dataToken = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            RegisterViewModel::class.java
        )
        dataToken.setRegister(username, password,user, requireContext())
        dataToken.getToken().observe(requireActivity(), Observer { token ->
            if (token != null) {
                val token = token.token
                AppPref.token = token
                Log.d("regis",token.toString())
                val intents = Intent(requireActivity(), HomePageActivity::class.java)
                intents.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intents)
            }
        })
    }

}