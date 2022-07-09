package com.apps.finalproject.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.apps.finalproject.databinding.FragmentLoginBinding
import com.apps.finalproject.remote.body.LoginBody
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.view.HomePageActivity
import com.apps.finalproject.utils.Utils

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
//    private lateinit var dataToken: ModelviewToken
    private val loginViewModel : LoginViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

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
                    val loginBody = LoginBody(email,password)
                    loginViewModel.Login(loginBody)
                    loginViewModel.getToken().observe(requireActivity()) {
                        Log.d("token0",it.token)
                        if (it.token != ""){
                            Log.d("token",it.token)
                            startActivity(Intent(requireContext(), HomePageActivity::class.java))
                        }else {
                            Utils.peringatan(requireContext(),"password salah")
                        }
                    }
                }
            }
        }
    }

//    fun logoin(username: String, password: String) {
//        Log.d("yang", "email $username")
//        dataToken = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
//            ModelviewToken::class.java
//        )
//        dataToken.setCurrentLogin(username, password, requireContext())
//        dataToken.getToken().observe(requireActivity(), Observer { token ->
//            if (token != null) {
//                val token = token.token
//                val jwt = JWT(token)
//                val uid = jwt.getClaim("userId")
//                val email = jwt.getClaim("userId")
//                AppPref.userId = uid.asString().toString()
//                Log.d("itoken", uid.asString().toString())
//            }
//        })
//    }
}