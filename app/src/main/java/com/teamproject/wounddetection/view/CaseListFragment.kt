package com.teamproject.wounddetection.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.teamproject.wounddetection.data.model.Case
import com.teamproject.wounddetection.databinding.FragmentCaseListBinding
import com.teamproject.wounddetection.utils.Status
import com.teamproject.wounddetection.viewmodel.AppViewModelProvider
import com.teamproject.wounddetection.viewmodel.PatientDataViewModel

class CaseListFragment : Fragment() {
    private lateinit var binding: FragmentCaseListBinding

    private lateinit var adapter: CaseAdapter

    private val args: CaseListFragmentArgs by navArgs()

    private val viewModel: PatientDataViewModel by viewModels { AppViewModelProvider.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkPatient(args.patietnCode.toString())
        setupView()
        setupObserver()
    }

    private fun setupView() {
        binding.apply {
            rvCases.layoutManager = LinearLayoutManager(context)
            adapter = CaseAdapter(mutableListOf(), ::onCaseClick)
            rvCases.adapter = adapter
            pullToRefresh.setOnRefreshListener {
                viewModel.checkPatient(args.patietnCode.toString())
            }
        }
    }

    private fun setupObserver() {
        viewModel.patient.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        binding.pullToRefresh.isRefreshing = true
                    }
                    Status.SUCCESS -> {
                        binding.pullToRefresh.isRefreshing = false
                        it.data?.let { it1 -> updateData(it1.cases) }
                    }
                    Status.ERROR -> {
                        binding.pullToRefresh.isRefreshing = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun onCaseClick(case: Case) {
        val action = CaseListFragmentDirections.actionCaseListFragmentToReportFragment(case)
        findNavController().navigate(action)
    }

    private fun updateData(cases: List<Case>) {
        adapter.clear()
        adapter.addData(cases)
        adapter.notifyDataSetChanged()
    }
}