package com.teamproject.wounddetection.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.teamproject.wounddetection.data.model.Case
import com.teamproject.wounddetection.databinding.FragmentCaseListBinding

class CaseListFragment : Fragment() {
    private lateinit var binding: FragmentCaseListBinding

    private lateinit var adapter: CaseAdapter

    private val args: CaseListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.apply {
            rvCases.layoutManager = LinearLayoutManager(context)
            adapter = CaseAdapter(args.cases.toMutableList(), ::onCaseClick)
            rvCases.adapter = adapter
        }
    }

    private fun onCaseClick(case: Case) {
        val action = CaseListFragmentDirections.actionCaseListFragmentToReportFragment(case)
        findNavController().navigate(action)
    }

}