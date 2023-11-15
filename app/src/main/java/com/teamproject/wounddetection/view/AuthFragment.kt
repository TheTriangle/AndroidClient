package com.teamproject.wounddetection.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.teamproject.wounddetection.R
import com.teamproject.wounddetection.databinding.FragmentAuthBinding
import com.teamproject.wounddetection.viewmodel.AuthViewModel

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null

    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels { AuthViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.buttonSignIn.setOnClickListener {
            viewModel.makeSignInRequest(
                binding.editTextLogin.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }
        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(R.id.action_AuthFragment_to_CameraFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}