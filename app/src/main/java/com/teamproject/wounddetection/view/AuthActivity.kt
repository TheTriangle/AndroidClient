package com.teamproject.wounddetection.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.teamproject.wounddetection.databinding.AuthActivityBinding
import com.teamproject.wounddetection.viewmodel.AuthViewModel

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: AuthActivityBinding
    private val viewModel: AuthViewModel by viewModels { AuthViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSignIn.setOnClickListener {
            viewModel.makeSignInRequest(
                binding.editTextLogin.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }
    }
}