package com.teamproject.wounddetection.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.teamproject.wounddetection.databinding.FragmentPatientDataBinding
import com.teamproject.wounddetection.utils.Status
import com.teamproject.wounddetection.viewmodel.AppViewModelProvider
import com.teamproject.wounddetection.viewmodel.PatientDataViewModel

class PatientDataFragment : Fragment() {

    private var _binding: FragmentPatientDataBinding? = null

    private val binding get() = _binding!!

    private val viewModel: PatientDataViewModel by viewModels { AppViewModelProvider.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPatientDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObserver()
    }

    private fun setupView() {
        binding.btnEnterPatientCode.setOnClickListener {
            viewModel.checkPatient(binding.etPatientCode.text.toString())
        }
    }

    private fun setupObserver() {
        viewModel.patient.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.apply {
                            btnEnterPatientCode.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            val action = PatientDataFragmentDirections.actionPatientDataFragmentToCameraFragment(it.data!!)
                            findNavController().navigate(action)
                            viewModel.resetPatient()
                        }
                    }
                    Status.ERROR -> {
                        binding.apply {
                            btnEnterPatientCode.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    Status.LOADING -> {
                        binding.apply {
                            btnEnterPatientCode.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}