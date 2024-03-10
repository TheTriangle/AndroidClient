package com.teamproject.wounddetection.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamproject.wounddetection.R
import com.teamproject.wounddetection.databinding.FragmentReportBinding

class ReportFragment : Fragment() {

    private lateinit var binding: FragmentReportBinding

    private lateinit var adapter: ReportAdapter

    private val args: ReportFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.apply {
            rvReports.layoutManager = LinearLayoutManager(context)
            adapter = ReportAdapter(requireContext(), args.case.reports.toList())
            rvReports.adapter = adapter
            tvCaseTitle.text = getString(
                R.string.case_title,
                "${args.case.doctor.name}, ${args.case.doctor.email}",
                args.case.date
            )
        }
    }
}