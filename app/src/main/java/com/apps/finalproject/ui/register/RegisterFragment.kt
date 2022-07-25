package com.apps.finalproject.ui.register

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentRegisterBinding
import com.apps.finalproject.remote.model.User
import com.apps.finalproject.ui.adapter.KurirAdapter
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
                !email.contains('@') ->{
                    binding.edtEmail.error = "Masukkan Email dengan Benar"
                }
                password.isEmpty() -> {
                    binding.edtPassword.error = "Masukkan Password"
                }
                else -> {
                    binding.progress.visibility = View.VISIBLE
                    val user = User(name,"skinteyp")
                    val jsonuser = JSONObject()
                    jsonuser.put("name", name);
                    jsonuser.put("skinType", "skinteype");
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
                dialogRegistered(R.layout.dialog_registered)
            }
            binding.progress.visibility = View.GONE
        })
    }

    private fun dialogRegistered(layoutId: Int) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutId)

        val lp = WindowManager.LayoutParams()
        if (dialog.window != null) {

            lp.copyFrom(dialog.window?.attributes)
            lp.gravity = Gravity.CENTER
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            val positiveButton = dialog.findViewById<Button>(R.id.btn_dialog_login)

            positiveButton.setOnClickListener {
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                dialog.hide()
            }
            dialog.show()
            dialog.window?.attributes = lp
        }
    }

}