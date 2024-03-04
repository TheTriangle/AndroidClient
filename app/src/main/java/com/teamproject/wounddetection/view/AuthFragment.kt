package com.teamproject.wounddetection.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.teamproject.wounddetection.R
import com.teamproject.wounddetection.databinding.FragmentAuthBinding
import com.teamproject.wounddetection.utils.Status
import com.teamproject.wounddetection.viewmodel.AppViewModelProvider
import com.teamproject.wounddetection.viewmodel.AuthViewModel

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null

    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels { AppViewModelProvider.Factory }

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
        setupObserver()
    }

    private fun setupView() {
        binding.buttonSignIn.setOnClickListener {
            viewModel.makeSignInRequest(
                binding.editTextLogin.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }
    }

    private fun setupObserver() {
        viewModel.auth.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.apply {
                        buttonSignIn.visibility = View.VISIBLE
                        buttonRegister.visibility = View.VISIBLE
                        pgAuth.visibility = View.GONE
                    }
                    val action = AuthFragmentDirections.actionLoginFragmentToPatientDataFragment()
                    findNavController().navigate(
                        action, NavOptions.Builder()
                            .setPopUpTo(R.id.LoginFragment, true)
                            .build()
                    )
                }

                Status.LOADING -> {
                    binding.apply {
                        buttonSignIn.visibility = View.GONE
                        buttonRegister.visibility = View.GONE
                        pgAuth.visibility = View.VISIBLE
                    }
                }

                Status.ERROR -> {
                    binding.apply {
                        buttonSignIn.visibility = View.VISIBLE
                        buttonRegister.visibility = View.VISIBLE
                        pgAuth.visibility = View.GONE
                    }
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}