package com.apps.finalproject.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentLoginBinding
import com.apps.finalproject.remote.body.LoginBody
import com.apps.finalproject.remote.body.TokenBody
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.home.HomePageActivity
import com.apps.finalproject.utils.Utils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    val RC_SIGN_IN = 1111
    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    private val googleViewModel: LoginGoogleViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    lateinit var gso: GoogleSignInOptions

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
        binding.tvDaftar.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment)
        )

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

        binding.appCompatImageButton.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

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
                    binding.progress.visibility = View.VISIBLE
                    val loginBody = LoginBody(email, password)
                    loginViewModel.Login(loginBody)
                    loginViewModel.getToken().observe(requireActivity()) {
                        Log.d("token0", it.token)
                        if (it.token != "") {
                            Log.d("token", it.token)
                            val intents = Intent(requireContext(), HomePageActivity::class.java)
                            intents.flags =
                                Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intents)
                        } else {
                            Utils.peringatan(requireContext(), "password salah")
                        }
                        binding.progress.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Log.d("accunt", account.idToken.toString())
            Log.d("accunt", account.toString())
            val token = TokenBody(account.idToken.toString())
            googleViewModel.LoginGoogle(token)
            googleViewModel.getToken().observe(requireActivity()) {
                if (it.token != "") {
                    Log.d("token", it.token)
                    val intents = Intent(requireContext(), HomePageActivity::class.java)
                    intents.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intents)
                } else {
                    Utils.peringatan(requireContext(), "password salah")
                }
            }
        } catch (e: ApiException) {
            Log.w("accunt", "signInResult:failed code=" + e.statusCode)
        }
        binding.progress.visibility = View.INVISIBLE
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